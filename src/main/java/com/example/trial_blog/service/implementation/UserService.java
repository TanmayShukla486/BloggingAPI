package com.example.trial_blog.service.implementation;

import com.example.trial_blog.custom_exception.UserNotFoundException;
import com.example.trial_blog.entity.user_entity.Role;
import com.example.trial_blog.entity.user_entity.User;
import com.example.trial_blog.entity.dto.JwtResponse;
import com.example.trial_blog.entity.dto.UserInputDTO;
import com.example.trial_blog.entity.dto.UserOutputDTO;
import com.example.trial_blog.repository.RoleRepository;
import com.example.trial_blog.repository.UserRepository;
import com.example.trial_blog.service.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceInterface {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authManager;

    private UserOutputDTO convert(User user) {
        return new UserOutputDTO(user.getUsername(), user.getRole().getRole(), user.getCreatedAt(), user.getUpdatedAt());
    }

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       AuthenticationManager authManager,
                       JwtService jwtService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.authManager = authManager;
        this.jwtService = jwtService;
        bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
    }

    @Override
    public List<UserOutputDTO> getAllUsers() {
        return userRepository.findAll().stream().map(this::convert).toList();
    }

    @Override
    public ResponseEntity<JwtResponse> loginUser(UserInputDTO user) throws UserNotFoundException {
        Authentication authentication = authManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        JwtResponse jwtResponse;
        if (authentication.isAuthenticated()) {
            User user1 = userRepository.findByUsername(user.getUsername())
                    .orElseThrow(() -> new UserNotFoundException("User not found"));
            String role = user1.getRole().getRole();
            jwtResponse = new JwtResponse(200,
                    "Login Successful",
                    jwtService.generateToken(user.getUsername(), role));
            return ResponseEntity.ok(jwtResponse);
        } else {
            jwtResponse = new JwtResponse(401, "Login Unsuccessful", null);
            return ResponseEntity.status(401).body(jwtResponse);
        }
    }

    @Override
    public User registerUser(UserInputDTO userInputDTO) {
        Optional<Role> saved = roleRepository.findByRole(userInputDTO.getRole());
        Role role;
        if (saved.isPresent()) role = saved.get();
        else {
            role = new Role();
            role.setId(0); role.setRole(userInputDTO.getRole());
        }
        User user = new User();
        user.setUsername(userInputDTO.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(userInputDTO.getPassword()));
        user.setRole(role);
        return userRepository.save(user);
    }
}
