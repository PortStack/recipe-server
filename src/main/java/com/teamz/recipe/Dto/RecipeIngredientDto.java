package com.teamz.recipe.Dto;

import com.teamz.recipe.domain.recipe.RecipeIngredientEntity;
import lombok.*;

public class RecipeIngredientDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request{
        Long id;
        String name;
        int count;
        String unit;
    }

    @RequiredArgsConstructor
    @Getter
    public static class Response {
        private final Long id;
        private final String name;
        private final int count;
        private final String unit;

        public Response(RecipeIngredientEntity recipeIngredients){
            this.id = recipeIngredients.getId();
            this.name = recipeIngredients.getIngredient().getName();
            this.count = recipeIngredients.getCount();
            this.unit = recipeIngredients.getUnit();
        }
    }
}
