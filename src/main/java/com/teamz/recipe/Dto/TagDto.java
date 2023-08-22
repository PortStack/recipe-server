package com.teamz.recipe.Dto;

import com.teamz.recipe.domain.RecipeTagMap;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class TagDto {
    @RequiredArgsConstructor
    @Getter
    public static class Response {
        private final String tag;

        public Response(RecipeTagMap recipeTagMap){
            this.tag = recipeTagMap.getTag().getName();
        }
    }
}
