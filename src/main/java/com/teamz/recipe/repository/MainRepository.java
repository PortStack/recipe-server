package com.teamz.recipe.repository;

import com.teamz.recipe.domain.Recipe;

import java.util.List;

public interface MainRepository {

    void setRecipe(Recipe recipe);

    List<Recipe> getRecipe();

    int storeSize();


}
