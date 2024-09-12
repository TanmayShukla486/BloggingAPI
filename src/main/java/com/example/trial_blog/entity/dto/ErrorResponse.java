package com.example.trial_blog.entity.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ErrorResponse {
    private int status;
    private String message;
}
