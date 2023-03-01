package com.example.spring_board.entity;

import com.example.spring_board.dto.BoardrequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Board extends Timestamped{ // 클래스 : 객체틀 // DB의 찐 객체 모음

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String password;

    public Board(BoardrequestDto boardrequestDto) { // 임마 이거 제일 싸없새다. 1시간 증발 꼭 확인하자 이놈 이거 드라마틱 하다.
        this.title = boardrequestDto.getTitle();
        this.content = boardrequestDto.getContent();
        this.author = boardrequestDto.getAuthor();
        this.password = boardrequestDto.getPassword();
    }

    public void update(BoardrequestDto boardrequestDto) {
        this.title = boardrequestDto.getTitle();
        this.content = boardrequestDto.getContent();
        this.author = boardrequestDto.getAuthor();
    }

}