package com.example.trial_blog.custom_exception;

import com.example.trial_blog.entity.user_entity.User;

public class UserNotFoundException extends Exception{

    public UserNotFoundException(String message) {
        super(message);
    }

    public static UserNotFoundException getInstance(String message) {
        return new UserNotFoundException(message);
    }
}
