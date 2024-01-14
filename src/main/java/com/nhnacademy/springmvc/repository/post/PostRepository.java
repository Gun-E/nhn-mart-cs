package com.nhnacademy.springmvc.repository.post;

import com.nhnacademy.springmvc.domain.post.Post;
import com.nhnacademy.springmvc.domain.post.Type;
import java.util.List;
import java.util.Map;

public interface PostRepository {

    Post register(String id, String title, String content, Type type, String fileName, String filePath);

    List<Post> getPosts(String Id);

    Post getPost(long postId);

    List<Post> getAdminPosts();

    void submitAnswer(Long postId, String answerContent, String adminName);
    void saveFileName(long postId, String fileName);
}
