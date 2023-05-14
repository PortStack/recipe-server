package com.teamz.recipe.Dto;

import com.teamz.recipe.domain.RecipeEntity;
import com.teamz.recipe.domain.RecipeIngredientEntity;
import com.teamz.recipe.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public class RecipeDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {
        private Long id;
        private String title;
        private String writer;
        private String views;
        private User user;
        private List<RecipeIngredientEntity> recipeIngredientEntities = new ArrayList<>();
        private List<CookOrderDto.Request> cookOrders = new ArrayList<>();

        /* Dto -> Entity */
        public RecipeEntity toEntity(){
            RecipeEntity recipe = RecipeEntity.builder()
                    .id(id)
                    .title(title)
                    .writer(writer)
                    .views(0)
                    .user(user)
                    .build();

            return recipe;
        }
    }
}
