package com.example.spring_board.controller;

import com.example.spring_board.dto.BoardrequestDto;
import com.example.spring_board.entity.Board;
import com.example.spring_board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 조회 부분
    @GetMapping("/api/posts")
    public List<Board> getBoards() {
        return boardService.getBoards();
    }

    // 작성 부분
    @PostMapping("/api/post")
    public Board createBoard(@RequestBody BoardrequestDto boardrequestDto) { // @RequestBody BoardrequestDto boardrequestDto : Board에 있는 DB에 저장
        return boardService.createBoard(boardrequestDto);
    }

    // 수정 부분
    @PutMapping("/api/post/{id}") // @PathVariable : URL 변수명를 사용할때 사용, 특정 파라미터를 처리 할수 있게 도와준다.
    public Long updateBoard(@PathVariable Long id, @RequestBody BoardrequestDto boardrequestDto) {
        return boardService.update(id, boardrequestDto);
    }

    // 선택한 게시글 삭제 API
    @DeleteMapping("/api/post/{id}")
    public Long deleteBoard(@PathVariable Long id, @RequestBody BoardrequestDto boardrequestDto) { // @RequestBody BoardrequestDto boardrequestDto :
        return boardService.delete(id, boardrequestDto);
    }

    //선택한 게시글 조회
    @GetMapping("/api/post/{id}")
    public Board getBoardOne(@PathVariable Long id) {

        return boardService.getBoardOne(id);
    }

}
