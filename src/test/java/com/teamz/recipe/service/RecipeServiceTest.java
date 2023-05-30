package com.teamz.recipe.service;

import com.teamz.recipe.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class RecipeServiceTest {

    @Autowired RecipeService recipeService;
    @Autowired RecipeRepository recipeRepository;



}