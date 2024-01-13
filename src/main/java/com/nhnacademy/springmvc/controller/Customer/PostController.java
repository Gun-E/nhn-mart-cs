package com.nhnacademy.springmvc.controller.Customer;

import com.nhnacademy.springmvc.domain.post.Post;
import com.nhnacademy.springmvc.domain.post.Type;
import com.nhnacademy.springmvc.exception.PostNotFoundException;
import com.nhnacademy.springmvc.repository.post.PostRepository;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customer/post")
public class PostController {
    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
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
        return "post/postView";
    }

    @GetMapping("/{postId}/modify")
    public String postModifyForm(@ModelAttribute("post") Post post,
                                 Model model) {
        if (Objects.isNull(post)) {
            model.addAttribute("exception", new PostNotFoundException());
            return "error";
        }

        model.addAttribute("post", post);
        return "post/postModify";
    }

    @GetMapping("/list")
    public String listPosts(@RequestParam(name = "category", required = false) String category, HttpServletRequest request, Model model) {
        String id = String.valueOf(request.getSession().getAttribute("customerId"));
        List<Post> posts = postRepository.getPosts(id);

        if (category != null && !category.isEmpty()) {
            posts = posts.stream()
                    .filter(post -> post.getType().equals(Type.valueOf(category)))
                    .collect(Collectors.toList());
        }

        model.addAttribute("filteredPosts", posts);
        return "post/postList";
    }
}
