package com.teamz.recipe.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    private String name;

    @JoinColumn(name = "UserEntity")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private UserEntity userEntity;

    public void setMember(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
