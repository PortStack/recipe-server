package com.teamz.recipe.repository;

import com.teamz.recipe.domain.CommentEntity;
import com.teamz.recipe.domain.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<CommentEntity, String> {
    /* 게시글 댓글 목록 가져오기 */
    List<CommentEntity> getCommentByRecipeOrderById(RecipeEntity recipe);

    Optional<CommentEntity> findByRecipe_IdAndId(Long recipe_id, Long id);
}
