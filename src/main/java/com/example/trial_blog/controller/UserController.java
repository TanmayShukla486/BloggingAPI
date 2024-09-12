package com.example.trial_blog.controller;

import com.example.trial_blog.entity.user_entity.User;
import com.example.trial_blog.entity.dto.JwtResponse;
import com.example.trial_blog.entity.dto.UserInputDTO;
import com.example.trial_blog.entity.dto.UserOutputDTO;
import com.example.trial_blog.service.implementation.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserInputDTO user) {
        System.out.println(user);
        return ResponseEntity.ok(userService.registerUser(user));
    }

    // for dev phase
    /**MARKED FOR REMOVAL*/
    @GetMapping("/all")
    public ResponseEntity<List<UserOutputDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> loginUser(@RequestBody UserInputDTO user) {
        return userService.loginUser(user);
    }

}
