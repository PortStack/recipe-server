package com.teamz.recipe.repository;

import com.teamz.recipe.domain.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByAccount(String account);

    Optional<UserEntity> findById(Long id);

    Optional<UserEntity> findByNickname(String nickname);

}
