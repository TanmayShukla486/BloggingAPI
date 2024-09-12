package com.example.trial_blog.repository;

import com.example.trial_blog.custom_exception.LikeNotFoundException;
import com.example.trial_blog.entity.blog_entity.Blog;
import com.example.trial_blog.entity.blog_entity.BlogLike;
import com.example.trial_blog.entity.user_entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<BlogLike, Integer> {

    Optional<BlogLike> findByUserAndBlog(User user, Blog blog);

    int countByBlog(Blog blog);

    boolean existsByUserAndBlog(User user, Blog blog);

}
