package com.example.trial_blog.custom_exception;

public class AlreadyLikeBlogException extends Exception{

    public AlreadyLikeBlogException(String message) {
        super(message);
    }

    public static AlreadyLikeBlogException getInstance(String message) {
        return new AlreadyLikeBlogException(message);
    }

}
