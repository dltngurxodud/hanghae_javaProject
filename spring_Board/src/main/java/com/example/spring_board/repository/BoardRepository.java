package com.example.spring_board.repository;

import com.example.spring_board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> { // 무조건 인터페이스로 만들어야 한다. 기능 구현이 많기 때문에

    List<Board> findAllByOrderByModifiedAtDesc();

    Optional<Object> findByIdAndPassword(Long id, String password); // 옵셔널 확인 필요

}
