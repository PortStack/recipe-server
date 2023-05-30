package com.teamz.recipe.domain;

import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="USER")
@Entity
public class UserEntity extends TimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false, unique = true)
    private String userId;

    @Column(nullable = false, unique = true)
    private String encryptedPwd;

    @Column(columnDefinition = "TEXT", nullable = false,unique = true)
    private String email;

    @Column(nullable = false, length = 50, unique = true)
    private String nickname;

    @Builder
    public UserEntity(
            String email,
            String nickname,
            String userId,
            String encryptedPwd
    ) {
        this.email = email;
        this.nickname = nickname;
        this.userId = userId;
        this.encryptedPwd = encryptedPwd;
    }

}
