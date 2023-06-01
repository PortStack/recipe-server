package com.teamz.recipe.Dto;

import com.teamz.recipe.domain.User;
import com.teamz.recipe.domain.UserEntity;
import lombok.*;

@Data
public class UserDto {
    private String email;
    private String password;
    private String nickname;
    private String phoneNumber;
    private String userId;
    private String createdDate;
    private String modifiedDate;
    private String encryptedPwd;
    private String Role;

    @Builder
    public UserDto(
            String email,
            String password,
            String nickname,
            String phoneNumber,
            String userId,
            String createdDate,
            String modifiedDate,
            String encryptedPwd,
            String Role
    ) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.userId = userId;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.encryptedPwd = encryptedPwd;
        this.Role = Role;
    }
}
