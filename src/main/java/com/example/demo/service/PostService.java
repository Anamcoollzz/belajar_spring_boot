package com.example.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {

    Page<com.example.demo.model.Post> getAllPosts(Pageable pageable);

    com.example.demo.model.Post getPostById(Long id);

    com.example.demo.model.Post createPost(com.example.demo.model.Post post);

    com.example.demo.model.Post updatePost(Long id, com.example.demo.model.Post post);

    void deletePost(Long id);
}
