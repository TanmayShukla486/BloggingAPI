package com.example.trial_blog.custom_exception;

public class BlogNotFoundException extends Exception{
    public BlogNotFoundException(String message) {
        super(message);
    }

    public static BlogNotFoundException getInstance(String message) {
        return new BlogNotFoundException(message);
    }
}
