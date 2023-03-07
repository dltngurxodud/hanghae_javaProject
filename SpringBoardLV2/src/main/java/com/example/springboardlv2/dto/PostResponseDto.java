package com.example.springboardlv2.dto;


import com.example.springboardlv2.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class PostResponseDto {

    private Long id;

    private String title;

    private String content;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
    }
}
