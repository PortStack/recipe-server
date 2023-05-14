package com.teamz.recipe.repository;

import com.teamz.recipe.domain.Recipe;
import com.teamz.recipe.domain.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> {
}
