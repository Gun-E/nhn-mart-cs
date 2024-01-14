package com.nhnacademy.springmvc.validator;

import com.nhnacademy.springmvc.domain.post.AnswerRegisterRequest;
import com.nhnacademy.springmvc.domain.post.PostRegisterRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AnswerRequestValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return AnswerRequestValidator.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AnswerRegisterRequest request = (AnswerRegisterRequest) target;
        String answerContent = request.getAnswerContent();
        if (answerContent.length() > 100) {
            errors.rejectValue("answerContent", "", "content max length is 40000");
        }
    }
}
