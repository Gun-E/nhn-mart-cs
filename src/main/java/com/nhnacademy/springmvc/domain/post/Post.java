package com.nhnacademy.springmvc.domain.post;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

public class Post {
    @Getter
    private String id;

    @Getter
    private final String title;

    @Getter
    private final String content;

    @Getter
    private final Type type;

    @Getter
    private final LocalDateTime createdAt;

    @Getter
    @Setter
    private String fileName;
    @Getter
    @Setter
    private String filePath;

    @Getter
    @Setter
    private LocalDateTime answerCreatedAt;

    @Getter
    @Setter
    private boolean answer;

    @Getter
    @Setter
    private String answerContent;
    @Getter
    @Setter
    private String adminName;
    @Getter
    @Setter
    private Long postId;

    public static Post create(String id, String title, String content, Type type) {
        return new Post(id, title, content, type);
    }

    public Post(String id, String title, String content, Type type) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.type = type;
        createdAt = LocalDateTime.now();
        answer = false;
        answerCreatedAt = null;
        answerContent = null;
        adminName = null;
        postId = null;
        fileName = null;
        filePath = null;
    }
}
