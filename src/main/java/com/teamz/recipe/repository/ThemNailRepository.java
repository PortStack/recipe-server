package com.teamz.recipe.repository;

import com.teamz.recipe.domain.RecipeEntity;
import com.teamz.recipe.domain.ThumbNailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemNailRepository extends JpaRepository<ThumbNailEntity, Long> {
}
