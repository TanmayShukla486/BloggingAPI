package com.example.trial_blog.repository;

import com.example.trial_blog.entity.blog_entity.Comment;
import com.example.trial_blog.entity.blog_entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    int countByComment(Comment comment);

}
