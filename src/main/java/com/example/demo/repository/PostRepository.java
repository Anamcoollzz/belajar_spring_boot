package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<com.example.demo.model.Post, Long> {

}
