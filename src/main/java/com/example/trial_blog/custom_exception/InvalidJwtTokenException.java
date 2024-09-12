package com.example.trial_blog.custom_exception;

public class InvalidJwtTokenException extends Exception{
    public InvalidJwtTokenException(String message) {
        super(message);
    }

    public static InvalidJwtTokenException getInstance(String message) {
        return new InvalidJwtTokenException(message);
    }
}
