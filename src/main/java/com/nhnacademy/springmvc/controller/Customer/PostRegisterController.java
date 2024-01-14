package com.nhnacademy.springmvc.controller.Customer;

import com.nhnacademy.springmvc.domain.post.Post;
import com.nhnacademy.springmvc.domain.post.PostRegisterRequest;
import com.nhnacademy.springmvc.exception.ValidationFailedException;
import com.nhnacademy.springmvc.repository.post.PostRepository;
import com.nhnacademy.springmvc.validator.PostRegisterRequestValidator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/customer/post/register")
@Slf4j
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
                                     @RequestParam("file") MultipartFile file,
                                     BindingResult bindingResult,
                                     HttpServletRequest request) throws IOException {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        Resource resource = null;
        Path filePath = null;
        if (!file.isEmpty()) {
            resource = new ClassPathResource("/");
            log.debug("resourceFolder : {}", resource.getURI());
            filePath = Paths.get(resource.getURI()).resolve(Objects.requireNonNull(file.getOriginalFilename()));
            log.debug("filePath : {}", filePath);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        }

        String id = String.valueOf(request.getSession().getAttribute("customerId"));
        Post post = postRepository.register(id, postRequest.getTitle(), postRequest.getContent(), postRequest.getType(), file.getOriginalFilename(), String.valueOf(filePath));

        ModelAndView mav = new ModelAndView("redirect:/customer/post/list");
        mav.addObject("post", post);

        return mav;
    }

    @InitBinder("postRegisterRequest")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

}
