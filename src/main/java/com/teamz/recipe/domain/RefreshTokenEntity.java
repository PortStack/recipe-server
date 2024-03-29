package com.teamz.recipe.domain;

import jakarta.persistence.*;
import lombok.*;

//redis로 옮겨야함
@Table(name="refresh_token")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class RefreshTokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;
    private String value;
}
