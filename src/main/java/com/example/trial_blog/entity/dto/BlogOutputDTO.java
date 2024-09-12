package com.example.trial_blog.entity.dto;

import com.example.trial_blog.entity.blog_entity.Blog;
import com.example.trial_blog.entity.blog_entity.BlogLike;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BlogOutputDTO implements Comparable<BlogOutputDTO>{

    private String title;
    private String content;
    private String author;
    private String category;
    private int likes;

    public static BlogOutputDTO getInstance(Blog blog) {
        List<BlogLike> list = blog.getLikes();
        return new BlogOutputDTO(
                blog.getTitle(),
                blog.getContent(),
                blog.getUser().getUsername(),
                blog.getCategory().getCategory(),
                list != null ? list.size() : 0
        );
    }

    @Override
    public int compareTo(BlogOutputDTO o) {
        return this.likes - o.likes;
    }
}
