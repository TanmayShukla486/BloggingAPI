package com.example.trial_blog.entity.blog_entity;

import com.example.trial_blog.entity.user_entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "blog")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_id")
    private long id;
    @Column(name = "title", columnDefinition = "varchar (150)", nullable = false)
    private String title;
    @Column(name = "content", columnDefinition = "text", nullable = false)
    private String content;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id")
    private Category category;
    @OneToMany(mappedBy = "blog", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> comments;
    @ManyToOne
    private User user;
    @Column(name = "created_at", columnDefinition = "timestamptz default now()")
    @CreationTimestamp
    private Timestamp createdAt;
    @Column(name = "updated_at", columnDefinition = "timestamptz default now()")
    @UpdateTimestamp
    private Timestamp updatedAt;
    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
    private List<BlogLike> likes;

}
