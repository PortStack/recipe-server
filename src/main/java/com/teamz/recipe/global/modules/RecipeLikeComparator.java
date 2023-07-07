package com.teamz.recipe.global.modules;

import com.teamz.recipe.domain.RecipeEntity;

import java.util.Comparator;

public class RecipeLikeComparator implements Comparator<RecipeEntity> {
    @Override
    public int compare(RecipeEntity r1, RecipeEntity r2) {
        if (r1.getLikes() > r2.getLikes()) {
            return 1;
        } else if (r1.getLikes() < r2.getLikes()) {
            return -1;
        }
        return 0;
    }
}
