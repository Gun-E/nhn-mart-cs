package com.nhnacademy.springmvc.repository.post;

import com.nhnacademy.springmvc.domain.post.Post;
import com.nhnacademy.springmvc.domain.post.Type;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.function.Function;

@Repository("postRepository")
public class PostRepositoryImpl implements PostRepository {
    private final List<Post> posts = new ArrayList<>();


    @Override
    public Post register(String id, String title, String content, Type type) {
        Post post = Post.create(id, title, content, type);
        post.setPostId((long) posts.size());
        posts.add(post);
        return post;
    }


    @Override
    public List<Post> getPosts(String id) {
        return posts.stream()
                .filter(post -> Objects.equals(post.getId(),id))
                .collect(Collectors.toList());
    }

    @Override
    public Post getPost(long postId) {
        return posts.get((int) postId);
    }

    @Override
    public List<Post> getAdminPosts() {
        return posts.stream()
                .filter(post -> !post.isAnswer())
                .collect(Collectors.toList());
    }

    @Override
    public void submitAnswer(Long postId, String answerContent, String adminName) {
        Post post = getPost(postId);
        post.setAnswer(true);
        post.setAnswerContent(answerContent);
        post.setAdminName(adminName);
        post.setAnswerCreatedAt(LocalDateTime.now());
    }
}
