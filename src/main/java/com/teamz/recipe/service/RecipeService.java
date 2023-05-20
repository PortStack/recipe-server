package com.teamz.recipe.service;

import com.teamz.recipe.Dto.CookOrderDto;
import com.teamz.recipe.Dto.RecipeDto;
import com.teamz.recipe.Dto.RecipeIngredientDto;
import com.teamz.recipe.domain.*;
import com.teamz.recipe.modules.FileHandler;
import com.teamz.recipe.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Optional;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final CookOrderRepository cookOrderRepository;
    private final CookOrderImageRepository cookOrderImageRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;
    private final ThemNailRepository themNailRepository;
    private final IngredientRepository ingredientRepository;
    private final FileHandler fileHandler;


    /* CREATE */
    public Long save(RecipeDto.Request recipeDto) throws Exception {

        RecipeEntity recipe = recipeDto.toEntity();

        List<CookOrderDto.Request> cookOrdersList = recipeDto.getCookOrders();
        List<RecipeIngredientDto.Request> ingredientList = recipeDto.getRecipeIngredients();
        System.out.println(ingredientList.get(0).getName());

        RecipeEntity result = recipeRepository.save(recipe);

        toEntityCookOrder(cookOrdersList,recipeDto.getOrderImage(),result);
        toEntityRecipeIngredient(ingredientList, result);

        return result.getId();

    }

    /* READ 게시글 리스트 조회 readOnly 속성으로 조회속도 개선 */
    @Transactional(readOnly = true)
    public RecipeDto.Response findById(Long id) {
        RecipeEntity posts = recipeRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id: " + id));

        return new RecipeDto.Response(posts);
    }

    public void saveThemNailImage(List<MultipartFile> themNailImages, RecipeEntity recipe) throws Exception {
        for(MultipartFile themNailImage : themNailImages){
            Image image = fileHandler.parseFileInfo(themNailImage);

            ThumbNailEntity thumbNail = ThumbNailEntity.builder()
                    .originFileName(image.getOriginFileName())
                    .fullPath(image.getFullPath())
                    .fileSize(image.getFileSize())
                    .recipe(recipe)
                    .build();

            themNailRepository.save(thumbNail);
        }
    }

    // 레시피 재료 저장
    public void toEntityRecipeIngredient(
            List<RecipeIngredientDto.Request> recipeIngredientList,
            RecipeEntity recipe ) throws Exception {

        if(!recipeIngredientList.isEmpty()){
            for(RecipeIngredientDto.Request recipeIngredient : recipeIngredientList){
                // 재료 가져와서 재료 저장 안되어있으면 저장 하도록 해야함
                Optional<Ingredient> ingredientNull = ingredientRepository.findByName(recipeIngredient.getName());
                Ingredient ingredient;

                if(ingredientNull.isPresent()){
                    ingredient = ingredientNull.get();
                }else{
                    ingredient = ingredientRepository.save(Ingredient.builder().name(recipeIngredient.getName()).build());
                }

                RecipeIngredientEntity recipeIngredientEntity = RecipeIngredientEntity.builder()
                        .count(recipeIngredient.getCount())
                        .unit((recipeIngredient.getUnit()))
                        .ingredient(ingredient)
                        .recipe(recipe)
                        .build();

                recipeIngredientRepository.save(recipeIngredientEntity);

            }
        }

    }

    //받은 조리 순서를 저장
    public void toEntityCookOrder(
            List<CookOrderDto.Request> CookPostorder,
            List<MultipartFile> orderImages,
            RecipeEntity recipe
            ) throws Exception {

        HashMap<String,Image> imageMap = new HashMap<>();
        if(!orderImages.isEmpty()){
            for(MultipartFile orderImage : orderImages ){
                Image image = fileHandler.parseFileInfo(orderImage);
                imageMap.put(image.getOriginFileName(),image);
            }
        }

        if(!CookPostorder.isEmpty()){
            for(CookOrderDto.Request cookOrder : CookPostorder){

                //리팩토링 필요
                if(imageMap.containsKey(cookOrder.getOrderImageName())){
                    Image image = imageMap.remove(cookOrder.getOrderImageName());
                    CookOrderImage orderImage = CookOrderImage.builder()
                            .originFileName(image.getOriginFileName())
                            .fullPath(image.getFullPath())
                            .fileSize(image.getFileSize())
                            .build();

                    CookOrder cookOrderEntity = CookOrder.builder()
                            .sequence(cookOrder.getSequence())
                            .content(cookOrder.getContent())
                            .cookOrderImage(cookOrderImageRepository.save(orderImage))
                            .recipe(recipe)
                            .build();

                    cookOrderRepository.save(cookOrderEntity);
                }

            }
        }

    }
}
