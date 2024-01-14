package com.nhnacademy.springmvc.controller.admin;

import com.nhnacademy.springmvc.domain.post.AnswerRegisterRequest;
import com.nhnacademy.springmvc.domain.post.Post;
import com.nhnacademy.springmvc.domain.post.Type;
import com.nhnacademy.springmvc.exception.PostNotFoundException;
import com.nhnacademy.springmvc.exception.ValidationFailedException;
import com.nhnacademy.springmvc.repository.AdminRepository;
import com.nhnacademy.springmvc.repository.post.PostRepository;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@Slf4j
public class AdminController {
    private final PostRepository postRepository;
    private final AdminRepository adminRepository;

    public AdminController(PostRepository postRepository, AdminRepository adminRepository) {
        this.postRepository = postRepository;
        this.adminRepository = adminRepository;
    }

    @GetMapping("/list")
    public String listPosts(@RequestParam(name = "category", required = false) String category, HttpServletRequest request, Model model) {
        List<Post> posts = postRepository.getAdminPosts();

        if (category != null && !category.isEmpty()) {
            posts = posts.stream()
                    .filter(post -> post.getType().equals(Type.valueOf(category)))
                    .collect(Collectors.toList());
        }

        model.addAttribute("filteredPosts", posts);
        return "admin/csManagement";
    }

    @GetMapping("/{postId}")
    public String viewPost(@PathVariable long postId,
                           ModelMap modelMap) {
        Post post = postRepository.getPost(postId);
        if (Objects.isNull(post)) {
            modelMap.put("exception", new PostNotFoundException());
            return "error";
        }
        modelMap.put("post", post);
        return "admin/adminPostView";
    }

    @PostMapping("/answer")
    public String submitAnswer(@RequestParam("postId") Long postId,
                               @RequestParam("answerContent") String answerContent,
                               @Validated @ModelAttribute AnswerRegisterRequest answerRegisterRequest,
                               BindingResult bindingResult,
                               HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        String id = String.valueOf(request.getSession().getAttribute("adminId"));
        String adminName = adminRepository.getAdminName(id);
        postRepository.submitAnswer(postId, answerContent, adminName);
        return "redirect:/admin/list";
    }
}
