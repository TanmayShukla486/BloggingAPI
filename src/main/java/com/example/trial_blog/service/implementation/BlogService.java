package com.example.trial_blog.service.implementation;

import com.example.trial_blog.custom_exception.BlogNotFoundException;
import com.example.trial_blog.custom_exception.CategoryNotFoundException;
import com.example.trial_blog.custom_exception.UserNotFoundException;
import com.example.trial_blog.entity.blog_entity.Blog;
import com.example.trial_blog.entity.blog_entity.Category;
import com.example.trial_blog.entity.dto.BlogInputDTO;
import com.example.trial_blog.entity.dto.BlogOutputDTO;
import com.example.trial_blog.entity.user_entity.User;
import com.example.trial_blog.repository.BlogRepository;
import com.example.trial_blog.repository.CategoryRepository;
import com.example.trial_blog.repository.UserRepository;
import com.example.trial_blog.service.interfaces.BlogServiceInterface;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService implements BlogServiceInterface {

    private final BlogRepository blogRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public BlogService(BlogRepository blogRepository,
                       UserRepository userRepository,
                       CategoryRepository categoryRepository) {
        this.blogRepository = blogRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<BlogOutputDTO> getAllBlogs() {
        List<Blog> blogs = blogRepository.findAll();
        return blogs.stream().map(BlogOutputDTO::getInstance).toList();
    }

    @Override
    @Transactional
    public BlogOutputDTO postBlog(BlogInputDTO blog) throws UserNotFoundException {
        // Checking if there is an existing category
        Category category = categoryRepository.findByCategory(blog.getCategory())
                .orElse(new Category(0, blog.getCategory()));
        // Finding the details of the user
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        // creating the blog to be saved
        Blog toBeSaved = new Blog();
        toBeSaved.setTitle(blog.getTitle());
        toBeSaved.setCategory(category);
        toBeSaved.setContent(blog.getContent());
        toBeSaved.setUser(user);
        Blog saved = blogRepository.save(toBeSaved);
        return BlogOutputDTO.getInstance(saved);
    }

    @Override
    public List<BlogOutputDTO> getBlogsByLikes() {
        List<Blog> blogs = blogRepository.findAll();
        List<BlogOutputDTO> response = new java.util.ArrayList<>(blogs.stream().map(BlogOutputDTO::getInstance).toList());
        Collections.sort(response);
        return response;
    }

    @Override
    public List<BlogOutputDTO> getBlogsByCategory(String category) throws CategoryNotFoundException{
        Optional<Category> response = categoryRepository.findByCategory(category);
        if (response.isEmpty()) throw new CategoryNotFoundException("Category Not Found");
        Category cat = response.get();
        List<Blog> blogs = blogRepository.findByCategory(cat);
        return blogs.stream().map(BlogOutputDTO::getInstance).toList();
    }

    @Override
    public List<BlogOutputDTO> getBlogsByAuthor(String author) throws UserNotFoundException{
        User resp = userRepository.findByUsername(author)
                .orElseThrow(() -> new UserNotFoundException("Author not found"));
        List<Blog> blogs = blogRepository.findByUser(resp);
        return blogs.stream().map(BlogOutputDTO::getInstance).toList();
    }

    @Override
    public List<BlogOutputDTO> getBlogsByAuthorAndCategory(String author,
                                                           String category) throws UserNotFoundException,
            CategoryNotFoundException {
        User user = userRepository.findByUsername(author)
                .orElseThrow(() -> new UserNotFoundException("Author not found"));
        Category cat = categoryRepository.findByCategory(category)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));
        List<Blog> blogs = blogRepository.findByUserAndCategory(user, cat);
        return blogs.stream().map(BlogOutputDTO::getInstance).toList();
    }

    @Transactional
    @Override
    public String deleteBlog(long blogId) throws BlogNotFoundException {
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new BlogNotFoundException("Blog not found"));
        blogRepository.delete(blog);
        return "Blog with id=" + blogId + " deleted successfully";
    }
}
