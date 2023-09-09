package com.teamz.recipe.repository;

import com.teamz.recipe.domain.recipe.CommentEntity;
import com.teamz.recipe.domain.recipe.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    /* 게시글 댓글 목록 가져오기 */
    List<CommentEntity> getCommentByRecipeOrderById(RecipeEntity recipe);

    Optional<CommentEntity> findByRecipe_IdAndId(Long recipe_id, Long id);
}
