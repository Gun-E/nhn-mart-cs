package com.nhnacademy.springmvc.controller.Customer;

import com.nhnacademy.springmvc.domain.post.Post;
import com.nhnacademy.springmvc.domain.post.PostRegisterRequest;
import com.nhnacademy.springmvc.exception.ValidationFailedException;
import com.nhnacademy.springmvc.repository.post.PostRepository;
import com.nhnacademy.springmvc.validator.PostRegisterRequestValidator;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/customer/post/register")
public class PostRegisterController {
    private final PostRepository postRepository;

    private final PostRegisterRequestValidator validator;

    public PostRegisterController(PostRepository postRepository, PostRegisterRequestValidator validator) {
        this.postRepository = postRepository;
        this.validator = validator;
    }

    @GetMapping
    public String userRegisterForm() {
        return "post/postRegister";
    }

    @PostMapping
    public ModelAndView registerPost(@Validated @ModelAttribute PostRegisterRequest postRequest,
                                     BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        String id = String.valueOf(request.getSession().getAttribute("customerId"));
        Post post = postRepository.register(id, postRequest.getTitle(), postRequest.getContent(),postRequest.getType());

        ModelAndView mav = new ModelAndView("redirect:/customer/post/list");
        mav.addObject("post", post);

        return mav;
    }

    @InitBinder("postRegisterRequest")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

}
