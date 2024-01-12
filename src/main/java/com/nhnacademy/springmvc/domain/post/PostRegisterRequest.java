package com.nhnacademy.springmvc.domain.post;

import lombok.Value;

@Value
public class PostRegisterRequest {
    String title;
    String content;
}
