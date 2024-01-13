package com.nhnacademy.springmvc.domain.post;

import javax.validation.constraints.Size;
import lombok.Value;

@Value
public class PostRegisterRequest {
    @Size(min = 2, max = 200, message = "제목은 2자 이상 200자 이하로 입력해주세요.")
    String title;
    @Size(max = 40000, message = "본문은 40,000자 이하로 입력해주세요.")
    String content;
    Type type;
}
