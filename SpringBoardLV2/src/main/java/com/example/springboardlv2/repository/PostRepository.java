package com.example.springboardlv2.repository;


import com.example.springboardlv2.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByOrderByModifiedAtDesc(); // Page를 써야하나?


//    Optional<Post> findById(Long id); 기본적으로 있는 친구
}
