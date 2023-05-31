package com.teamz.recipe.repository;

import com.teamz.recipe.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<UserEntity,Long> {
}
