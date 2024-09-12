package com.example.trial_blog.service.implementation;

import com.example.trial_blog.service.interfaces.JwtServiceInterface;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService implements JwtServiceInterface {

    private final String secretKey;

    public JwtService() {
        try{
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
            SecretKey sk = keyGenerator.generateKey();
            secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private <T> T getClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    public String extractUsername(String token) {
        return getClaim(token, Claims::getSubject);
    }

    private Date extractExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }

    private String extractRole(String token) {
        return getClaim(token, claim -> claim.get("role", String.class));
    }

    private boolean isExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    @Override
    public String generateToken(String username, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 10))
                .and()
                .signWith(getSecretKey())
                .compact();
    }

    @Override
    public boolean validateToken(String token, UserDetails userDetails) {
        String userName = extractUsername(token);
        return userName.equals(userDetails.getUsername()) && !isExpired(token);
    }

    @Override
    public SecretKey getSecretKey() {
        byte[] secret = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(secret);
    }
}
