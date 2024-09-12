package com.example.trial_blog.entity.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CommentInputDTO {
    private long blogId;
    private String content;
}
