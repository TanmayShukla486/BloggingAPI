package com.example.trial_blog.entity.blog_entity;

import com.example.trial_blog.entity.user_entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reply_like",
        uniqueConstraints = {
                @UniqueConstraint(name = "unique_reply_user", columnNames = {"reply_id", "user_id"})
        }
)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReplyLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_like_id")
    private long id;
    @ManyToOne
    @JoinColumn(name = "reply_id")
    Reply reply;
    @JoinColumn(name = "user_id")
    @ManyToOne
    User user;

}