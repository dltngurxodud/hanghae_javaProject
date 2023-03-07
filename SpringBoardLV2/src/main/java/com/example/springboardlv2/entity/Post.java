package com.example.springboardlv2.entity;


import com.example.springboardlv2.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter // 세터 박은 이유가 무엇?
@RequiredArgsConstructor
@Entity
public class Post extends Timestamped{

    @Id // 여기도 아이디가 필요하죠...? 왜냐? 게시판을 찾아야 하니까 그런데 username이 고유하니까 필요없는지?
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    // 뭔가 username이랑 연결해야할것 같음. 연관관계를 형성해야 할것 같다. 외래키를 사용하여
    @Column(nullable = false)
    private String username;

    @ManyToOne // To 앞에 있는게 현재 엔티티를 말하는지?
    @JoinColumn //
    private User user; // post와 조인해서 다대일 관계 만들고

    public Post(PostRequestDto postRequestDto, User user) {
        this.title = postRequestDto.getTitle();
        this.content = postRequestDto.getContent();
        this.username = user.getUsername(); // PostRequestDto 여기에 username이 없는데 유저를 불러오지 못하니까 다른곳에서 연결
        this.user = user; // 이진님이 과제 끝내고
    }

    public void update(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.content = postRequestDto.getContent();

    }

}
