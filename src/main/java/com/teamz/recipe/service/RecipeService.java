package com.teamz.recipe.service;

import com.teamz.recipe.Dto.CookOrderDto;
import com.teamz.recipe.Dto.RecipeDto;
import com.teamz.recipe.Dto.RecipeIngredientDto;
import com.teamz.recipe.domain.*;
import com.teamz.recipe.modules.FileHandler;
import com.teamz.recipe.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final CookOrderRepository cookOrderRepository;
    private final CookOrderImageRepository cookOrderImageRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;
    private final IngredientRepository ingredientRepository;
    private final FileHandler fileHandler;


    /* CREATE */
    public Long save(RecipeDto.Request recipeDto) throws Exception {

        RecipeEntity recipe = recipeDto.toEntity();

        List<CookOrderDto.Request> cookOrdersList = recipeDto.getCookOrders();
        List<RecipeIngredientDto.Request> requestList = recipeDto.getRecipeIngredientEntities();

        recipe = toEntityCookOrder(cookOrdersList,recipe);
        recipe = toEntityRecipeIngredient(requestList, recipe);

        return recipeRepository.save(recipe).getId();

    }

    // 레시피 재료 저장
    public RecipeEntity toEntityRecipeIngredient(
            List<RecipeIngredientDto.Request> recipeIngredientList,
            RecipeEntity recipe ) throws Exception {

        if(!recipeIngredientList.isEmpty()){
            for(RecipeIngredientDto.Request recipeIngredient : recipeIngredientList){

                // 재료 가져와서 재료 저장 안되어있으면 저장 하도록 해야함
                RecipeIngredientEntity recipeIngredientEntity = RecipeIngredientEntity.builder()
                        .count(recipeIngredient.getCount())
                        .unit((recipeIngredient.getUnit()))
                        .build();

                recipe.addRecipeIngredients(recipeIngredientRepository.save(recipeIngredientEntity));

            }
        }

        return recipe;
    }

    //받은 조리 순서를 저장
    public RecipeEntity toEntityCookOrder(
            List<CookOrderDto.Request> CookPostorder,
            RecipeEntity recipe ) throws Exception {

        if(!CookPostorder.isEmpty()){
            for(CookOrderDto.Request cookOrder : CookPostorder){
                Image image = fileHandler.parseFileInfo(cookOrder.getOrderImage());
                CookOrderImage orderImage = CookOrderImage.builder()
                        .originFileName(image.getOriginFileName())
                        .fullPath(image.getFullPath())
                        .fileSize(image.getFileSize())
                        .build();

                CookOrder cookOrderEntity = CookOrder.builder()
                        .sequence(cookOrder.getSequence())
                        .content(cookOrder.getContent())
                        .cookOrderImage(cookOrderImageRepository.save(orderImage))
                        .build();

                recipe.addCookOrders(cookOrderRepository.save(cookOrderEntity));

            }
        }

        return recipe;
    }
}
