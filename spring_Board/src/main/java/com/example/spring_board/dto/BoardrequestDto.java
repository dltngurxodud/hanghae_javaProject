package com.example.spring_board.dto;

import lombok.Getter;


@Getter
public class BoardrequestDto { // 데이터 교환을 하기위해 사용하는 객체 // DB객체 연결하는 부분

    private String title;

    private String content;

    private String author;

    private String password;

}
