package com.example.trial_blog.entity.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class JwtResponse {

    private int statusCode;
    private String message;
    private String token;

}
