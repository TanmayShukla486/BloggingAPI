package com.example.trial_blog.entity.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BlogInputDTO {

    private String title;
    private String content;
    private String category;

}
