package com.example.trial_blog.controller;

import com.example.trial_blog.custom_exception.CategoryNotFoundException;
import com.example.trial_blog.custom_exception.UserNotFoundException;
import com.example.trial_blog.entity.blog_entity.Blog;
import com.example.trial_blog.entity.dto.BlogInputDTO;
import com.example.trial_blog.entity.dto.BlogOutputDTO;
import com.example.trial_blog.service.implementation.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BlogController {

    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping("/blog")
    public ResponseEntity<BlogOutputDTO> postBlog(@RequestBody BlogInputDTO blog) {
        return ResponseEntity.ok(blogService.postBlog(blog));
    }

    @GetMapping("/blog")
    public ResponseEntity<List<BlogOutputDTO>> getBlogs(
            @RequestParam(name = "author", required = false) String author,
            @RequestParam(name = "category", required = false) String category,
            @RequestParam(name = "sort", required = false) String sort,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit
    ) throws UserNotFoundException, CategoryNotFoundException {
        List<BlogOutputDTO> blogs;
        if (author == null && category == null) {
            blogs = blogService.getAllBlogs();
        } else if (author != null && category == null) {
            blogs = blogService.getBlogsByAuthor(author);
        } else if (author == null) {
            blogs = blogService.getBlogsByCategory(category);
        } else {
            blogs = blogService.getBlogsByAuthorAndCategory(author, category);
        }
        if (sort != null) {
            if (sort.equals("desc")) blogs.sort(Collections.reverseOrder());
            else Collections.sort(blogs);
        }
        if (limit > blogs.size())
            blogs = blogs.subList(0, limit - 1);
        return ResponseEntity.ok(blogs);
    }


}
