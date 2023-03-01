package com.example.spring_board.service;

import com.example.spring_board.dto.BoardrequestDto;
import com.example.spring_board.entity.Board;
import com.example.spring_board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public List<Board> getBoards() {
        return boardRepository.findAllByOrderByModifiedAtDesc();
    }

    @Transactional
    public Board createBoard(BoardrequestDto boardrequestDto) {
        Board board = new Board(boardrequestDto);
        boardRepository.save(board); // 꼭 소문자로 넣자 임마 이거도 싸없새다 이것때문에 10분이상 날렸다. 꼭 변수 쓰자 머리통 쓰지말자
        return board;
    }

    // 비밀번호 확인 메서드
    public void passCheck(Long id, BoardrequestDto boardrequestDto) {
        boardRepository.findByIdAndPassword(id, boardrequestDto.getPassword()).orElseThrow(
                () -> new IllegalArgumentException("비밀번호가 일치하지 않습니다.")
        );
    }

    @Transactional
    public Long update(Long id, BoardrequestDto boardrequestDto) {
        Board board = getBoardOne(id);

//      if (board.getPassword().equals(boardrequestDto.getPassword()))

        passCheck(id, boardrequestDto);

        board.update(boardrequestDto);

        return board.getId();
    }

    @Transactional
    public Long delete(Long id, BoardrequestDto boardrequestDto) {
        Board board = getBoardOne(id);

        passCheck(id, boardrequestDto);

        boardRepository.deleteById(id);
        return id;
    }

    @Transactional
    public Board getBoardOne(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글이 없습니다.")
        );
        return board;
    }
}
