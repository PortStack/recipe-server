package com.teamz.recipe.repository;

import com.teamz.recipe.domain.RecipeLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RecipeLikeRepository extends JpaRepository<RecipeLikeEntity,Long> {
    Optional<RecipeLikeEntity> findByRecipe_IdAndUser_Id(Long recipeId, Long UserId);
    void  deleteByRecipe_IdAndUser_Id(Long recipeId, Long UserId);
}
