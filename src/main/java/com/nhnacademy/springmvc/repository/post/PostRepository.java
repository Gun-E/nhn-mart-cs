package com.nhnacademy.springmvc.repository.post;

import com.nhnacademy.springmvc.domain.post.Post;

public interface PostRepository {
    boolean exists(long id);

    Post register(String title, String content);

    Post getPost(long id);

}
