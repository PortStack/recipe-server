package com.teamz.recipe.repository;

import com.teamz.recipe.domain.RecipeIngredientEntity;
import com.teamz.recipe.domain.RefreshTokenEntity;
import com.teamz.recipe.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity,Long> {
    Optional<RefreshTokenEntity> findByValue(String value);
}
