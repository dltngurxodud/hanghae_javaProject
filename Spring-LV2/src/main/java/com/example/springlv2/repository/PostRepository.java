package com.example.springlv2.repository;

import com.example.springlv2.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByOrderByModifiedAtDesc(); // Page를 써야하나?

    Optional<Post> findById(Long id);
}
