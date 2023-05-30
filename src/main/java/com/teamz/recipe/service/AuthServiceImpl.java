package com.teamz.recipe.service;


import com.teamz.recipe.Dto.UserDto;
import com.teamz.recipe.domain.User;
import com.teamz.recipe.domain.UserEntity;
import com.teamz.recipe.repository.AuthRepository;
import com.teamz.recipe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
@Slf4j
public class AuthServiceImpl implements AuthService{
    private final Pattern passwordPatten = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,10}$");
    private AuthRepository authRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    public AuthServiceImpl(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    /*
    * 회원 가입
    * */
    @Override
    public UserDto registerUser(UserDto userDto) {

        if(ConfirmationPassword(userDto.getPassword())){
            new IllegalArgumentException("비밀번호 조건이 맞지 않습니다 : ");
        }

        UserEntity userEntity = UserEntity.builder()
                .email(userDto.getEmail())
                .nickname(userDto.getNickname())
                .encryptedPwd(passwordEncoder.encode(userDto.getPassword()))
                .userId(UUID.randomUUID().toString())
                .build();

        authRepository.save(userEntity);

        return userDto.builder()
                .email(userEntity.getEmail())
                .nickname(userEntity.getNickname())
                .encryptedPwd(userEntity.getEncryptedPwd())
                .userId(userEntity.getUserId())
                .build();
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
