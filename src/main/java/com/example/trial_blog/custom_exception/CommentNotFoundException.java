package com.example.trial_blog.custom_exception;

public class CommentNotFoundException extends Exception{
    public CommentNotFoundException(String message) {
        super(message);
    }

    public static CommentNotFoundException getInstance(String message) {
        return new CommentNotFoundException(message);
    }
}
