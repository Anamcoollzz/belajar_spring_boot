package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Post;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/posts")
public class PostController {
    private final com.example.demo.service.PostService postService;
    private final com.example.demo.service.FileStorageService fileStorageService;

    // Define your endpoints here
    @GetMapping
    public ResponseEntity<java.util.List<com.example.demo.model.Post>> getAllPosts() {
        java.util.List<com.example.demo.model.Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @PostMapping
    public ResponseEntity<com.example.demo.model.Post> createPost(@RequestBody com.example.demo.model.Post post) {
        com.example.demo.model.Post createdPost = postService.createPost(post);
        return ResponseEntity.ok(createdPost);
    }

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> uploadFile(
            @org.springframework.web.bind.annotation.RequestParam("image") org.springframework.web.multipart.MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);

        Map<String, String> response = new HashMap<>();
        response.put("fileName", fileName);
        response.put("message", "File uploaded successfully");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPostById(@PathVariable Long id) {
        com.example.demo.model.Post post = postService.getPostById(id);

        if (post != null) {
            return ResponseEntity.ok(new HashMap() {
                {
                    put("message", "post retrieved successfully");
                    put("status", 200);
                    put("data", post);
                }
            });
        } else {
            Map<String, Object> error = new HashMap<>();
            error.put("message", "post not found");
            error.put("status", 404);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<com.example.demo.model.Post> updatePost(@PathVariable Long id, @RequestBody Post post) {
        com.example.demo.model.Post updatedPost = postService.updatePost(id, post);
        if (updatedPost != null) {
            return ResponseEntity.ok(updatedPost);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<java.util.Map<String, String>> deletePost(@PathVariable Long id) {
        if (postService.getPostById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(java.util.Map.of("message", "Post not found"));
        }
        postService.deletePost(id);
        return ResponseEntity.status(HttpStatus.OK).body(java.util.Map.of("message", "Post deleted successfully"));
    }

}
