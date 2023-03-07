package com.example.springboardlv2.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter
public class SignupRequestDto {

    @NotNull(message = "username은 필수 입니다.")
    @NotBlank
    @Size(min = 4, max = 10)
    @Pattern(regexp = "^[0-9a-z]*$", message = "아이디는 4~10자 영문 소문자(a~z), 숫자(0~9)를 사용하세요.")
    private String username;

    @NotNull(message = "username은 필수 입니다.")
    @NotBlank
    @Size(min = 8, max = 15)
    @Pattern(regexp = "^[0-9a-zA-Z]*$", message = "비밀번호는 8~15자 영문 대 소문자(a~z, A~Z), 숫자(0~9)를 사용하세요")
    private String password;

}
