package com.example.trial_blog.entity.blog_entity;

import com.example.trial_blog.entity.user_entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "comment_like",
        uniqueConstraints = {
            @UniqueConstraint(name = "unique_comment_user", columnNames = {"comment_id", "user_id"})
        }
)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CommentLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_like_id")
    private long id;
    @ManyToOne
    @JoinColumn(name = "comment_id")
    Comment comment;
    @JoinColumn(name = "user_id")
    @ManyToOne
    User user;

}
