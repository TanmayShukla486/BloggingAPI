package com.example.trial_blog.custom_exception;

public class CategoryNotFoundException extends Exception{

    public CategoryNotFoundException(String message) {
        super(message);
    }

    public static CategoryNotFoundException getInstance(String message) {
        return new CategoryNotFoundException(message);
    }
}
