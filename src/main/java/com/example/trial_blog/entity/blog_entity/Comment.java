package com.example.trial_blog.entity.blog_entity;

import com.example.trial_blog.entity.user_entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "comment")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private long id;
    @Column(name = "content", columnDefinition = "text", nullable = false)
    private String content;
    @ManyToOne
    private Blog blog;
    @ManyToOne
    private User user;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "comment")
    List<Reply> replies;
}
