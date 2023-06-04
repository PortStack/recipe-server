package com.teamz.recipe.Dto;

import com.teamz.recipe.domain.User;
import com.teamz.recipe.domain.UserEntity;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.parameters.P;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

public class AuthDto {
    @Getter
    public static class RequestRegister {
        @NotEmpty
        private String account;

        @NotEmpty
        @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$", message = "비밀번호 형식을 맞춰야합니다")
        private String password;

        @NotEmpty
        @Pattern(regexp = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$",
        message = "이메일 형식을 맞춰야합니다")
        private String email;

        @NotEmpty
        private String nickname;

    }

    @Getter
    public static class RequestLogin {
        @NotEmpty
        private String account;

        @NotEmpty
        private String password;
    }

    @Data
    @Builder
    public static class ResponseUser {
        private String email;
        private String nickname;
        private String userId;


    }

}
