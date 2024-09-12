package com.example.trial_blog.custom_exception;

public class LikeNotFoundException extends Exception{

    public LikeNotFoundException(String message) {
        super(message);
    }

    public static LikeNotFoundException getInstance(String message) {
        return new LikeNotFoundException(message);
    }

}
