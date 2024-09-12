package com.example.trial_blog.repository;

import com.example.trial_blog.entity.blog_entity.Comment;
import com.example.trial_blog.entity.blog_entity.CommentLike;
import com.example.trial_blog.entity.user_entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {

    List<Comment> getByUserAndComment(User user, Comment comment);

}
