package com.example.trial_blog.repository;

import com.example.trial_blog.entity.blog_entity.Blog;
import com.example.trial_blog.entity.blog_entity.Category;
import com.example.trial_blog.entity.user_entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {

    List<Blog> findByCategory(Category category);

    List<Blog> findByUser(User user);

    List<Blog> findByUserAndCategory(User user, Category category);

}
