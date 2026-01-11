package com.example.demo.service;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PostServiceImplementation implements PostService {

    private final com.example.demo.repository.PostRepository postRepository;

    // public PostServiceImplementation(com.example.demo.repository.PostRepository
    // postRepository) {
    // this.postRepository = postRepository;
    // }

    @Override
    public java.util.List<com.example.demo.model.Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public com.example.demo.model.Post getPostById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    @Override
    public com.example.demo.model.Post createPost(com.example.demo.model.Post post) {
        post.setCreatedAt(new java.util.Date());
        post.setUpdatedAt(new java.util.Date());
        return postRepository.save(post);
    }

    @Override
    public com.example.demo.model.Post updatePost(Long id, com.example.demo.model.Post post) {
        com.example.demo.model.Post existingPost = postRepository.findById(id).orElse(null);
        if (existingPost != null) {
            existingPost.setTitle(post.getTitle());
            existingPost.setContent(post.getContent());
            existingPost.setAuthor(post.getAuthor());
            existingPost.setUpdatedAt(new java.util.Date());
            return postRepository.save(existingPost);
        }
        return null;
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

}
