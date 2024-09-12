package com.example.trial_blog.service.interfaces;

import com.example.trial_blog.custom_exception.BlogNotFoundException;
import com.example.trial_blog.custom_exception.CategoryNotFoundException;
import com.example.trial_blog.custom_exception.UserNotFoundException;
import com.example.trial_blog.entity.dto.BlogInputDTO;
import com.example.trial_blog.entity.dto.BlogOutputDTO;
import jakarta.transaction.Transactional;

import java.util.List;

public interface BlogServiceInterface {

    BlogOutputDTO postBlog(BlogInputDTO blog) throws UserNotFoundException;
    List<BlogOutputDTO> getAllBlogs();
    List<BlogOutputDTO> getBlogsByLikes();
    List<BlogOutputDTO> getBlogsByCategory(String category) throws CategoryNotFoundException;
    List<BlogOutputDTO> getBlogsByAuthor(String author) throws UserNotFoundException;

    List<BlogOutputDTO> getBlogsByAuthorAndCategory(String author,
                                                    String category) throws UserNotFoundException,
            CategoryNotFoundException;

    @Transactional
    String deleteBlog(long blogId) throws BlogNotFoundException;
}
