package com.teamz.recipe.repository;

import com.teamz.recipe.domain.BoardLikeEntity;
import com.teamz.recipe.domain.RecipeLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardLikeRepository extends JpaRepository<BoardLikeEntity,Long> {
    Optional<BoardLikeEntity> findByBoard_IdAndUser_Id(Long recipeId, Long UserId);
    void  deleteByBoard_IdAndUser_Id(Long recipeId, Long UserId);
}