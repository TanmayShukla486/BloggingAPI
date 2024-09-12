package com.example.trial_blog.entity.blog_entity;

import com.example.trial_blog.entity.user_entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "blog_likes"
        ,uniqueConstraints = {
        @UniqueConstraint(name = "unique_blog_user", columnNames = {"blog_id","user_id"})
    })
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BlogLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private int likeId;
    @NaturalId
    @ManyToOne(optional = false)
    @JoinColumn(name = "blog_id")
    private Blog blog;
    @NaturalId
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;
}
