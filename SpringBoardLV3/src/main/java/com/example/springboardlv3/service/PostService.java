package com.example.springboardlv3.service;

import com.example.springboardlv3.dto.CommentResponseDto;
import com.example.springboardlv3.dto.PostRequestDto;
import com.example.springboardlv3.dto.PostResponseDto;
import com.example.springboardlv3.dto.ResponseDto;
import com.example.springboardlv3.entity.Comment;
import com.example.springboardlv3.entity.Post;
import com.example.springboardlv3.entity.User;
import com.example.springboardlv3.jwt.JwtUtil;
import com.example.springboardlv3.repository.CommentRepository;
import com.example.springboardlv3.repository.PostRepository;
import com.example.springboardlv3.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service // 싸없새 노트에 적어야지
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    // 이미 로그인을 한 상태이니까 다른 방법으로 접근해야할것 같다. 토큰 확인

    // 생성 부분
    @Transactional
    public PostResponseDto createPost(PostRequestDto postRequestDto, HttpServletRequest request) { // 토큰이 확인되면 생성되는 구문을 만들자
        User user = userInfo(request);

        Post post = postRepository.saveAndFlush(new Post(postRequestDto, user)); // saveAndFlush : 메소드는 실행중(트랜잭션)에 즉시 data를 flush 한다. saveAndFlush() 메소드는 Spring Data JPA 에서 정의한 JpaRepository 인터페이스의 메소드이다. saveAndFlush() 메소드는 즉시 DB 에 변경사항을 적용하는 방식

        return new PostResponseDto(post);
    }

    // 조회 부분
    @Transactional(readOnly = true)
    public List<PostResponseDto> getPost() {
//      return postRepository.findAllByOrderByCreatedAtDesc();
        List<Post> postList = postRepository.findAllByOrderByModifiedAtDesc(); // 게시글 조회
        List<PostResponseDto> postResponseDto = new ArrayList<>(); // 게시글 리스트 빈공간 만들기

        for (Post post : postList) { // post를 list 형식으로
            List<CommentResponseDto> commentResponseDtoList = new ArrayList<>(); // 댓글 빈공간
            List<Comment> commentList = post.getComment(); // 코멘트를 리스트에 가져온다.
            for (Comment comment : commentList) {
//                CommentResponseDto commentResponseDto = new CommentResponseDto(comment);
                commentResponseDtoList.add(new CommentResponseDto(comment)); // 뽑아온 CommentResponseDto 객체 값을 댓글list 빈공간에 넣어주기
            }
//            PostResponseDto postDto = new PostResponseDto(post, commentResponseDtoList); // Post post, List<CommentResponseDto> commentResponseDtoList
            // 뽑고 싶은 게시글 객체를 불러오고 해당 매개변수 타입에 맞는 변수를 쓴다. 게시글과 댓글을 postDto에 넣어서
            postResponseDto.add(new PostResponseDto(post, commentResponseDtoList)); // postResponseDto list 빈공간에 게시글(댓글포함)을 하나씩 추가된다.
        }
        return postResponseDto;
    }

    // 수정부분
    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto postRequestDto, HttpServletRequest request) {
        User user = userInfo(request); // request : 어떤 정보를 가져오기 위해 사용 토큰을 먼저 세팅을 해야 가져온다.
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시물이 존재하지 않습니다.")
        );

        post.getUser().getId(); // post에 있는 user객체를 뽑아와서 user객체 안에 있는 getid를 뽑아옴

        if (post.getUser().getId().equals(user.getId())) {
            post.update(postRequestDto);
        }
        else {
            throw new IllegalArgumentException("등록한 게시물만 수정할 수 있습니다.");
        }
        return new PostResponseDto(post);
    }

    // 상세 조회
    @Transactional
    public PostResponseDto getPostOne(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("조회하려는 게시물이 존재하지 않습니다.")
        );


        return new PostResponseDto(post);
    }

    // 삭제
    @Transactional
    public ResponseDto delete (Long id, HttpServletRequest request){ // 반환 타입을 전부 Dto로 해줘야 하나요?
        User user = userInfo(request);
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시물이 존재하지 않습니다.")
        );

        if (post.getUser().getId().equals(user.getId())) { // 수정한 부분
            commentRepository.deleteByPostId(id);
            postRepository.deleteById(id);
        }
        else {
            throw new IllegalArgumentException("등록한 게시물만 삭제할 수 있습니다.");
        }

        return new ResponseDto("삭제 완료", HttpStatus.OK.value());
    }




    private User userInfo(HttpServletRequest request) { // 여기 부분은 무엇인가? 유저 메서드를 끌고와서 토큰?
        String token = jwtUtil.resolveToken(request); // request로 사용자 정보 가져오기 토큰 가져오기
        Claims claims;

        if(token != null) {
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            }
            else {
                throw new IllegalArgumentException("토큰 에러");
            }
            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );
            return user;
        } else {
            throw new IllegalArgumentException("해당 토큰값과 일치하는 정보가 없습니다.");
        }
    }

}
