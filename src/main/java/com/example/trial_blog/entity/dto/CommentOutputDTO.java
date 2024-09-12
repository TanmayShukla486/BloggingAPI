package com.example.trial_blog.entity.dto;

import com.example.trial_blog.entity.blog_entity.Comment;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CommentOutputDTO {

    private String username;
    private long blogId;
    private String content;

    public static CommentOutputDTO getInstance(Comment comment) {
        return new CommentOutputDTO(
                comment.getUser().getUsername(),
                comment.getBlog().getId(),
                comment.getContent()
        );
    }

}
