package com.example.demo.service;

import java.util.List;

public interface PostService {

    List<com.example.demo.model.Post> getAllPosts();

    com.example.demo.model.Post getPostById(Long id);

    com.example.demo.model.Post createPost(com.example.demo.model.Post post);

    com.example.demo.model.Post updatePost(Long id, com.example.demo.model.Post post);

    void deletePost(Long id);
}
