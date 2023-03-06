package com.example.springlv2.service;

import com.example.springlv2.dto.PostRequestDto;
import com.example.springlv2.dto.PostResponseDto;
import com.example.springlv2.dto.ResponseDto;
import com.example.springlv2.entity.Post;
import com.example.springlv2.entity.User;
import com.example.springlv2.jwt.JwtUtil;
import com.example.springlv2.repository.PostRepository;
import com.example.springlv2.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service // 싸없새 노트에 적어야지
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    // 이미 로그인을 한 상태이니까 다른 방법으로 접근해야할것 같다. 토큰 확인

    // 생성 부분
    @Transactional
    public PostResponseDto createPost(PostRequestDto postRequestDto, HttpServletRequest request) { // 토큰이 확인되면 생성되는 구문을 만들자
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        // 토큰 확인
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("토큰 에러");
            }

            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            // 여기 밑에 오류가 뜨는 이유? 저장을 하는 부분 user.getusername()이 안되는이유 연관관계랑 이유가 있다. 한번 다시 짚고가자
            Post post = postRepository.saveAndFlush(new Post(postRequestDto, user)); // saveAndFlush : 메소드는 실행중(트랜잭션)에 즉시 data를 flush 한다. saveAndFlush() 메소드는 Spring Data JPA 에서 정의한 JpaRepository 인터페이스의 메소드이다. saveAndFlush() 메소드는 즉시 DB 에 변경사항을 적용하는 방식

            return new PostResponseDto(post);
        } else {
            return null;
        }
    }

    // 조회 부분
    @Transactional(readOnly = true)
    public List<Post> getPost() {
        return postRepository.findAllByOrderByModifiedAtDesc();
    }

    // 수정부분
    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto postRequestDto, HttpServletRequest request) {
        User user = userInfo(request); // request : 어떤 정보를 가져오기 위해 사용 토큰을 먼저 세팅을 해야 가져온다.
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시물이 존재하지 않습니다.")
        );
        if (post.getUsername().equals(user.getUsername())) {
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
        if (post.getUsername().equals(user.getUsername())) {
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
