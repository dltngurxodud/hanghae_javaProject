package com.example.springboardlv3.service;

import com.example.springboardlv3.dto.CommentRequestDto;
import com.example.springboardlv3.dto.CommentResponseDto;
import com.example.springboardlv3.entity.Comment;
import com.example.springboardlv3.entity.Post;
import com.example.springboardlv3.entity.User;
import com.example.springboardlv3.entity.UserRoleEnum;
import com.example.springboardlv3.jwt.JwtUtil;
import com.example.springboardlv3.repository.CommentRepository;
import com.example.springboardlv3.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final JwtUtil jwtUtil;

    private Post getPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
    }

    private Comment getComment(Long replyId){
        return commentRepository.findById(replyId).orElseThrow(
                () -> new IllegalArgumentException("댓글이 존재하지 않습니다.")
        );
    }

    private void checkRole(Long commentId, User user) {
        if (user.getRole() == UserRoleEnum.ADMIN) return;
        commentRepository.findByIdAndUser(commentId, user).orElseThrow(
                () -> new IllegalArgumentException("권한이 없습니다.")
        );
    }

    @Transactional
    public CommentResponseDto createComment(CommentRequestDto commentRequestDto, HttpServletRequest request) {
        User user = jwtUtil.getUser(request);
        Post post = getPost(commentRequestDto.getPostId());

        Comment comment = commentRepository.saveAndFlush(new Comment(commentRequestDto.getComment(), user, post));
        return new CommentResponseDto(comment, user.getUsername());
    }

    @Transactional
    public CommentResponseDto update(Long commentId, CommentRequestDto commentRequestDto, HttpServletRequest request) {
        User user = jwtUtil.getUser(request);
        Comment comment = getComment(commentId);
        checkRole(commentId, user);
        comment.update(commentRequestDto);
        return new CommentResponseDto(comment, user.getUsername());
    }

    @Transactional
    public ResponseEntity<String> delete(Long commentId, HttpServletRequest request) {
        User user = jwtUtil.getUser(request);
        getComment(commentId);
        checkRole(commentId, user);
        commentRepository.deleteById(commentId);
        return ResponseEntity.status(HttpStatus.OK).body("댓글 삭제 완료");
    }

}
