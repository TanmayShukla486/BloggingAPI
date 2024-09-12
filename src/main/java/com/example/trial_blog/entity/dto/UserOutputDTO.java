package com.example.trial_blog.entity.dto;


import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserOutputDTO {

    private String username;
    private String role;
    private Timestamp createdAt;
    private Timestamp updatedAt;

}
