package com.teamz.recipe.service;

import com.teamz.recipe.Dto.AuthDto;
import com.teamz.recipe.Dto.ResponseSingDto;
import com.teamz.recipe.domain.Authority;
import com.teamz.recipe.domain.UserEntity;
import com.teamz.recipe.global.jwt.JwtProvider;
import com.teamz.recipe.repository.AuthRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public ResponseSingDto login(AuthDto.RequestLogin request) throws Exception {
        UserEntity user = authRepository.findByAccount(request.getAccount()).orElseThrow(() ->
                new BadCredentialsException("잘못된 계정정보입니다."));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("잘못된 계정정보입니다.");
        }


        return ResponseSingDto.builder()
                .account(user.getAccount())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .roles(user.getRoles())
                .token(jwtProvider.createToken(user.getAccount(), user.getRoles()))
                .build();

    }

    public boolean register(AuthDto.RequestRegister request) throws Exception {
        try {
            if(request.getAccount().length() == 0){
                throw new BadCredentialsException("아이디가 존재하지 않습니다");
            }

            if(!ConfirmationPassword(request.getPassword())){
                throw new BadCredentialsException("비밀번호 조건이 틀렸습니다");
            }
            UserEntity user = UserEntity.builder()
                    .account(request.getAccount())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .nickname(request.getNickname())
                    .email(request.getEmail())
                    .build();

            user.setRoles(Collections.singletonList(Authority.builder().name("ROLE_USER").build()));

            authRepository.save(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("잘못된 요청입니다.");
        }
        return true;
    }

    public ResponseSingDto getUser(String account) throws Exception {
        UserEntity user = authRepository.findByAccount(account)
                .orElseThrow(() -> new Exception("계정을 찾을 수 없습니다."));
        return new ResponseSingDto(user);
    }

    public boolean ConfirmationPassword(String pwd){
        //비밀번호 재대로 작성했는지 테스트
        if(pwd.length() == 0){
            return false;
        }

        // 비밀번호 포맷 확인(영문, 특수문자, 숫자 포함 8자 이상)
        Pattern passPattern1 = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$");
        Matcher passMatcher1 = passPattern1.matcher(pwd);

        if(!passMatcher1.find()){
            return false;
        }
        return true;
    }

}
