package com.example.trial_blog.entity.user_entity;

import com.example.trial_blog.entity.blog_entity.Blog;
import com.example.trial_blog.entity.blog_entity.BlogLike;
import com.example.trial_blog.entity.blog_entity.Comment;
import com.example.trial_blog.entity.blog_entity.Reply;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "user_table")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;
    @Column(name = "username", nullable = false, unique = true, columnDefinition = "varchar(60)")
    private String username;
    @Column(name = "password", nullable = false, columnDefinition = "varchar(60)")
    private String password;
    @Column(name = "created_at", nullable = false, columnDefinition = "timestamptz default now()")
    @CreationTimestamp
    private Timestamp createdAt;
    @Column(name = "updated_at", nullable = false, columnDefinition = "timestamptz default now()")
    @UpdateTimestamp
    private Timestamp updatedAt;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "role_id")
    private Role role;
    @Column(name = "is_expired")
    private boolean isExpired;
    @Column(name = "is_inactive")
    private boolean isInActive;
    @Column(name = "bio", columnDefinition = "TEXT")
    private String bio;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Blog> blogs;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BlogLike> blogLikes;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reply> replies;
}
