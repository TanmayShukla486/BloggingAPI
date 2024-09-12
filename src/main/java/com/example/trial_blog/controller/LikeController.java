package com.example.trial_blog.controller;

import com.example.trial_blog.custom_exception.AlreadyLikeBlogException;
import com.example.trial_blog.custom_exception.BlogNotFoundException;
import com.example.trial_blog.custom_exception.LikeNotFoundException;
import com.example.trial_blog.custom_exception.UserNotFoundException;
import com.example.trial_blog.entity.dto.PrimitiveResponse;
import com.example.trial_blog.service.implementation.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/blog/like")
public class LikeController {

    private final LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/{blogId}")
    public ResponseEntity<PrimitiveResponse<Integer>> addLike(@PathVariable long blogId) throws BlogNotFoundException,
            UserNotFoundException,
            AlreadyLikeBlogException {
        return ResponseEntity.ok(new PrimitiveResponse<>(200, "Successful", likeService.addLike(blogId)));
    }

    @GetMapping("/{blogId}")
    public ResponseEntity<PrimitiveResponse<Integer>> getAllLikes(@PathVariable long blogId) throws BlogNotFoundException {
        return ResponseEntity.ok(
                new PrimitiveResponse<>(200, "Successful", likeService.getAllLikes(blogId))
        );
    }

    @DeleteMapping("/{blogId}")
    public ResponseEntity<PrimitiveResponse<Integer>> deleteLikes(@PathVariable long blogId) throws BlogNotFoundException,
            LikeNotFoundException, UserNotFoundException {
        return ResponseEntity.status(203)
                        .body(new PrimitiveResponse<>(203, "Successful", likeService.removeLike(blogId)));
    }

}
