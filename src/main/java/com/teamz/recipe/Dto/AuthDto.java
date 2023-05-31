package com.teamz.recipe.Dto;

import com.teamz.recipe.domain.User;
import com.teamz.recipe.domain.UserEntity;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

public class AuthDto {
    @Getter
    public static class RequestRegister {
        private String password;
        private String email;
        private String nickname;

    }

    @Getter
    public static class RequestLogin {
        private String email;

        private String password;
    }

    @Data
    @Builder
    public static class ResponseUser {
        private String email;
        private String nickname;
        private String userId;
        private String encryptedPwd;


    }
}
