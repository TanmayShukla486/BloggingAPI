package com.example.trial_blog.entity.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PrimitiveResponse<T>{

    int status;
    String message;
    T value;

}
