package com.teamz.recipe.service;

import com.teamz.recipe.domain.User;
import com.teamz.recipe.repository.MemoryUserRepository;
import java.util.Optional;

public class UserService {
    private final MemoryUserRepository userRepository = new MemoryUserRepository();

    /*
    회원가입
     */
    public String register(User user){
        validDuplicateUseId(user);
        validDuplicateUserNickName(user);
        userRepository.register(user);
        return user.getNickname();
    }

    /*
    아이디 중복 검사
     */
    private void validDuplicateUseId(User user){
        userRepository.findById(user.getId())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 아이디 입니다");
                });

    }

    /*
    닉네임 중복 검사
     */
    private void validDuplicateUserNickName(User user){
        userRepository.findByNickName(user.getNickname())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 아이디 입니다");
                });

    }

    /*
    비밀번호 유효성 검사
     */

    /*
    아이디 찾기
     */
    public Optional<User> findOne(Long userId) {
        return userRepository.findById(userId);
    }


}
