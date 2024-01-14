package com.nhnacademy.springmvc.domain.post;

import javax.validation.constraints.Size;
import lombok.Value;

@Value
public class AnswerRegisterRequest {
    @Size(max = 40000, message = "답변은 40,000자 이하로 입력해주세요.")
    String answerContent;
}
