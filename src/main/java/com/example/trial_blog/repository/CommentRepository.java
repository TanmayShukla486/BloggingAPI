package com.example.trial_blog.repository;

import com.example.trial_blog.entity.blog_entity.Blog;
import com.example.trial_blog.entity.blog_entity.Comment;
import com.example.trial_blog.entity.user_entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByBlog(Blog blog);

    List<Comment> findByUser(User user);

}
