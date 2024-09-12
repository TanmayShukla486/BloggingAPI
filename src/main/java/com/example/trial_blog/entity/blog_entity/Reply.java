package com.example.trial_blog.entity.blog_entity;


import com.example.trial_blog.entity.user_entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reply")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id")
    private long id;
    @Column(name = "content", columnDefinition = "text", nullable = false)
    private String content;
    @ManyToOne
    @JoinColumn(name = "comment_id")
    Comment comment;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

}
