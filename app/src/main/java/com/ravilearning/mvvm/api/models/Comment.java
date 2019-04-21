package com.ravilearning.mvvm.api.models;

public class Comment {

    private Long postId;
    private Long id;
    private String name;
    private String email;
    private String body;

    public Long getPostId() {
        return postId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getBody() {
        return body;
    }
}