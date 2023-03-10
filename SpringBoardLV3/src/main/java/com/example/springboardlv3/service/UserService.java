package com.example.springboardlv3.service;


import com.example.springboardlv3.dto.LoginRequestDto;
import com.example.springboardlv3.dto.SignupRequestDto;
import com.example.springboardlv3.entity.User;
import com.example.springboardlv3.entity.UserRoleEnum;
import com.example.springboardlv3.jwt.JwtUtil;
import com.example.springboardlv3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    // 회원가입
    @Transactional
    public void signup(SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();

        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) { // true면 실행 false면 통과
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;

        User user = new User(username, password, role);
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public void login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        // 사용자 확인
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );
        // 비밀번호 확인
        if(!user.getPassword().equals(password)){
            throw  new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername(), user.getRole())); // addHeader() : 그 키에 해당하는 값을 하나 더 추가한다.
        // 토큰 값을 셋팅하는 부분
    }
}
