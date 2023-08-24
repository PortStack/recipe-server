package com.teamz.recipe.repository;

import com.teamz.recipe.domain.recipe.RecipeTagMap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagMapRepository extends JpaRepository<RecipeTagMap, Long> {
}
