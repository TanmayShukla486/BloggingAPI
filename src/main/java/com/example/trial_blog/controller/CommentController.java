package com.example.trial_blog.controller;

import com.example.trial_blog.custom_exception.BlogNotFoundException;
import com.example.trial_blog.custom_exception.CommentNotFoundException;
import com.example.trial_blog.custom_exception.UserNotFoundException;
import com.example.trial_blog.entity.dto.CommentInputDTO;
import com.example.trial_blog.entity.dto.CommentOutputDTO;
import com.example.trial_blog.service.implementation.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blog/comment")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{blogId}")
    public ResponseEntity<List<CommentOutputDTO>> getAllComments(@PathVariable long blogId) throws BlogNotFoundException {
        return ResponseEntity.ok(commentService.getComments(blogId));
    }

    @PostMapping("/")
    public ResponseEntity<CommentOutputDTO> addComment(@RequestBody CommentInputDTO input) throws BlogNotFoundException,
            UserNotFoundException {
        return ResponseEntity.ok(commentService.addComment(input));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable(name = "commentId") long commentId) throws CommentNotFoundException {
        return ResponseEntity.status(203).body(commentService.deleteComment(commentId));
    }


}
