package com.example.trial_blog.service.interfaces;

import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;

public interface JwtServiceInterface {

    String generateToken(String username, String role);

    boolean validateToken(String token, UserDetails userDetails);

    SecretKey getSecretKey();

}
