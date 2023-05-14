package com.teamz.recipe.service;

import com.teamz.recipe.Dto.CookOrderDto;
import com.teamz.recipe.Dto.RecipeDto;
import com.teamz.recipe.domain.CookOrder;
import com.teamz.recipe.domain.CookOrderImage;
import com.teamz.recipe.domain.Image;
import com.teamz.recipe.domain.RecipeEntity;
import com.teamz.recipe.modules.FileHandler;
import com.teamz.recipe.repository.CookOrderImageRepository;
import com.teamz.recipe.repository.CookOrderRepository;
import com.teamz.recipe.repository.RecipeRepository;
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
    private final FileHandler fileHandler;


    /* CREATE */
    public Long save(RecipeDto.Request recipeDto) throws Exception {

        RecipeEntity recipe = recipeDto.toEntity();

        List<CookOrderDto.Request> cookOrdersDto = recipeDto.getCookOrders();

        if(!cookOrdersDto.isEmpty()){
            for(CookOrderDto.Request cookOrder : cookOrdersDto){
                Image image = fileHandler.parseFileInfo(cookOrder.getOrderImage());
                CookOrderImage orderImage = CookOrderImage.builder()
                        .originFileName(image.getOriginFileName())
                        .fullPath(image.getFullPath())
                        .fileSize(image.getFileSize())
                        .build();

                CookOrder cookOrderEntity = CookOrder.builder()
                        .order(cookOrder.getOrder())
                        .content(cookOrder.getContent())
                        .cookOrderImage(cookOrderImageRepository.save(orderImage))
                        .build();

                recipe.addCookOrders(cookOrderRepository.save(cookOrderEntity));

            }
        }

        return recipeRepository.save(recipe).getId();

    }
}
