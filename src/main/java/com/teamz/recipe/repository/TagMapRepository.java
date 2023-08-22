package com.teamz.recipe.repository;

import com.teamz.recipe.domain.RecipeTagMap;
import com.teamz.recipe.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagMapRepository extends JpaRepository<RecipeTagMap, Long> {
}
