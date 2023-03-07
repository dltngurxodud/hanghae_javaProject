package com.example.springboardlv2.controller;

import com.example.springboardlv2.dto.PostRequestDto;
import com.example.springboardlv2.dto.PostResponseDto;
import com.example.springboardlv2.dto.ResponseDto;
import com.example.springboardlv2.entity.Post;
import com.example.springboardlv2.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet. http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 조회 부분
    @GetMapping("/posts")
    public List<PostResponseDto> getPost() {
        return postService.getPost();
    }

    // 작성 부분
    @PostMapping("/post") // PostResponseDto를 쓰는 이유?
    public PostResponseDto createPost(@RequestBody PostRequestDto postRequestDto, HttpServletRequest request) { // @RequestBody BoardrequestDto boardrequestDto : Board에 있는 DB에 저장
        return postService.createPost(postRequestDto, request);
    }

    // 수정 부분
    @PutMapping("/post/{id}") // @PathVariable : URL 변수명를 사용할때 사용, 특정 파라미터를 처리 할수 있게 도와준다.
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto, HttpServletRequest request) {
        return postService.updatePost(id, postRequestDto, request);
    }

    // 선택한 게시글 삭제 API
    @DeleteMapping("/post/{id}")
    public ResponseDto delete(@PathVariable Long id, HttpServletRequest request) { // @RequestBody BoardrequestDto boardrequestDto : 쿠키를 가져오기 위해 사용
        return postService.delete(id, request );
    }

    //선택한 게시글 조회
    @GetMapping("/post/{id}")
    public PostResponseDto getBoardOne(@PathVariable Long id) {
        return postService.getPostOne(id);
    }
}
