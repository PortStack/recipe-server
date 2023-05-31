package com.teamz.recipe.repository;

import com.teamz.recipe.domain.Recipe;
import com.teamz.recipe.domain.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> {

    @Modifying
    @Query("update RecipeEntity p set p.views = p.views + 1 where p.id = :id")
    int updateView(Long id);
}
