package com.example.trial_blog.middleware;

import com.example.trial_blog.custom_exception.*;
import com.example.trial_blog.entity.blog_entity.Comment;
import com.example.trial_blog.entity.dto.ErrorResponse;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleJwtError(InvalidJwtTokenException e) {
        return ResponseEntity.status(401).body(new ErrorResponse(401, e.getMessage()));
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleBlogError(BlogNotFoundException e) {
        return ResponseEntity.status(404).body(new ErrorResponse(404, e.getMessage()));
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleLikeError(LikeNotFoundException e) {
        return ResponseEntity.status(404).body(new ErrorResponse(404, e.getMessage()));
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleExistingLikeError(AlreadyLikeBlogException e) {
        return ResponseEntity.status(403).body(new ErrorResponse(403, e.getMessage()));
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleCommentError(CommentNotFoundException e) {
        return ResponseEntity.status(404).body(new ErrorResponse(404, e.getMessage()));
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleUserException(UserNotFoundException e) {
        return ResponseEntity.status(404).body(new ErrorResponse(404, e.getMessage()));
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleGenericError(Exception e) {
        return ResponseEntity.status(501).body(new ErrorResponse(501, e.getMessage()));
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleCategoryError(CategoryNotFoundException e) {
        return ResponseEntity.status(404).body(new ErrorResponse(404, e.getMessage()));
    }
}
