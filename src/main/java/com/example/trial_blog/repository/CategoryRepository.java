package com.example.trial_blog.repository;

import com.example.trial_blog.entity.blog_entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Optional<Category> findByCategory(String category);

}
