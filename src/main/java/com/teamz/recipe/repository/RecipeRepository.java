package com.teamz.recipe.repository;

import com.teamz.recipe.domain.recipe.RecipeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> {

    Page<RecipeEntity> findByTitleContaining(String title, Pageable pageable);
    @Modifying
    @Query("update RecipeEntity p set p.views = p.views + 1 where p.id = :id")
    int updateView(@Param("id")Long id);

    @Modifying
    @Query("update RecipeEntity p set p.likes = p.likes - 1 where p.id = :id")
    int minusLike(@Param("id")Long id);

    @Modifying
    @Query("update RecipeEntity p set p.likes = p.likes + 1 where p.id = :id")
    int plusLike(@Param("id")Long id);

//    List<RecipeEntity> findByTitleContaining(String searchText);
}
