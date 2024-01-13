package com.nhnacademy.springmvc.repository.post;

import com.nhnacademy.springmvc.domain.post.Post;
import com.nhnacademy.springmvc.domain.post.Type;
import java.util.*;
import java.util.stream.Collectors;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.function.Function;

@Repository("postRepository")
public class PostRepositoryImpl implements PostRepository {
    private final List<Post> posts = new ArrayList<>();

//    @Override
//    public boolean exists(String id) {
//        return posts.stream()
//                .anyMatch(post -> Objects.equals(post.getId(), id));
//    }

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
}
