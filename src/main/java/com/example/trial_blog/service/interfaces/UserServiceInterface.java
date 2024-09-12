package com.example.trial_blog.service.interfaces;

import com.example.trial_blog.custom_exception.UserNotFoundException;
import com.example.trial_blog.entity.user_entity.User;
import com.example.trial_blog.entity.dto.JwtResponse;
import com.example.trial_blog.entity.dto.UserInputDTO;
import com.example.trial_blog.entity.dto.UserOutputDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserServiceInterface {

    User registerUser(UserInputDTO user);

    ResponseEntity<JwtResponse> loginUser(UserInputDTO user) throws UserNotFoundException;

    List<UserOutputDTO> getAllUsers();
}
