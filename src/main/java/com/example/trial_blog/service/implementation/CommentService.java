package com.example.trial_blog.service.implementation;

import com.example.trial_blog.custom_exception.BlogNotFoundException;
import com.example.trial_blog.custom_exception.CommentNotFoundException;
import com.example.trial_blog.custom_exception.UserNotFoundException;
import com.example.trial_blog.entity.blog_entity.Blog;
import com.example.trial_blog.entity.blog_entity.Comment;
import com.example.trial_blog.entity.dto.CommentInputDTO;
import com.example.trial_blog.entity.dto.CommentOutputDTO;
import com.example.trial_blog.entity.user_entity.User;
import com.example.trial_blog.repository.BlogRepository;
import com.example.trial_blog.repository.CommentRepository;
import com.example.trial_blog.repository.UserRepository;
import com.example.trial_blog.service.interfaces.CommentServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService implements CommentServiceInterface {

    private final BlogRepository blogRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(BlogRepository blogRepository, UserRepository userRepository, CommentRepository commentRepository) {
        this.blogRepository = blogRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentOutputDTO addComment(CommentInputDTO input) throws BlogNotFoundException, UserNotFoundException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        Blog blog = blogRepository.findById(input.getBlogId())
                .orElseThrow(() -> BlogNotFoundException.getInstance("Blog not found"));
        Comment comment = new Comment();
        comment.setBlog(blog);
        comment.setUser(user);
        comment.setContent(input.getContent());
        comment = commentRepository.save(comment);
        return CommentOutputDTO.getInstance(comment);
    }

    @Override
    public List<CommentOutputDTO> getComments(long blogId) throws BlogNotFoundException {
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> BlogNotFoundException.getInstance("Blog not found"));
        List<Comment> comments = commentRepository.findByBlog(blog);
        return comments.stream().map(CommentOutputDTO::getInstance).toList();
    }

    @Override
    public List<CommentOutputDTO> getCommentsByUser(String username) throws UserNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> UserNotFoundException.getInstance("User not found"));
        List<Comment> comments = commentRepository.findByUser(user);
        return comments.stream().map(CommentOutputDTO::getInstance).toList();
    }

    @Override
    public String deleteComment(long commentId) throws CommentNotFoundException {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> CommentNotFoundException.getInstance("Comment not found"));
        commentRepository.delete(comment);
        return "Comment deleted successfully";
    }
}
