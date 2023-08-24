package com.teamz.recipe.repository;

import com.teamz.recipe.domain.recipe.RecipeIngredientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredientEntity, String> {
}
