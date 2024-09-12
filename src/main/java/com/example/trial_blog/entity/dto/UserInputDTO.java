package com.example.trial_blog.entity.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserInputDTO {

    private String username;
    private String password;
    private String role;

}
