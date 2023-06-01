package com.teamz.recipe.Dto;
import com.teamz.recipe.domain.Authority;
import com.teamz.recipe.domain.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseSingDto {
    private String account;

    private String nickname;

    private String email;

    private List<Authority> roles = new ArrayList<>();

    private String token;

    public ResponseSingDto(UserEntity member) {
        this.account = member.getAccount();
        this.nickname = member.getNickname();
        this.email = member.getEmail();
        this.roles = member.getRoles();
    }

}