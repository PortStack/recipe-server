package com.teamz.recipe.repository;

import com.teamz.recipe.domain.Recipe;

import java.util.*;

public class MemoryMainRepository implements MainRepository{
    private static List<Recipe> store = new ArrayList<Recipe>();

    @Override
    public void setRecipe(Recipe recipe) {
        store.add(recipe);
    }

    @Override
    public List<Recipe> getRecipe() {
        return store;
    }

    @Override
    public int storeSize() { return store.size(); }

}
