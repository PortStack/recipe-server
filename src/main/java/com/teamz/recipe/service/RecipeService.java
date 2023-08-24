package com.teamz.recipe.service;

import com.teamz.recipe.Dto.CookOrderDto;
import com.teamz.recipe.Dto.RecipeDto;
import com.teamz.recipe.Dto.RecipeIngredientDto;
import com.teamz.recipe.domain.*;
import com.teamz.recipe.domain.recipe.*;
import com.teamz.recipe.global.modules.FileHandler;
import com.teamz.recipe.global.modules.RecipeLikeComparator;
import com.teamz.recipe.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RequiredArgsConstructor
@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final SearchRepository searchRepository;
    private final BoardRepository boardRepository;
    private final TagRepository tagRepository;
    private final CookOrderRepository cookOrderRepository;
    private final CookOrderImageRepository cookOrderImageRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;
    private final ThemNailRepository themNailRepository;
    private final IngredientRepository ingredientRepository;
    private final AuthRepository authRepository;
    private final RecipeLikeRepository recipeLikeRepository;
    private final FileHandler fileHandler;
    private final TagService tagService;


    /* CREATE */
    @Transactional
    public Long save(RecipeDto.Request recipeDto) throws Exception {
        System.out.println("title : " + recipeDto.getTitle());
        UserEntity user = authRepository.findByNickname(recipeDto.getWriter()).orElseThrow(() ->
                new IllegalArgumentException("해당 유저가 존재하지 않습니다" + recipeDto.getWriter()));

        System.out.println("닉네임:"+user.getNickname());

        RecipeEntity recipe = RecipeEntity.builder()
                .id(recipeDto.getId())
                .title(recipeDto.getTitle())
                .user(user)
                .views(0)
                .build();

        List<CookOrderDto.Request> cookOrdersList = recipeDto.getCookOrders();
        List<RecipeIngredientDto.Request> ingredientList = recipeDto.getRecipeIngredients();

        RecipeEntity result = recipeRepository.save(recipe);

        saveThemNailImage(recipeDto.getThemNail(),result);
        toEntityCookOrder(cookOrdersList,recipeDto.getOrderImage(),result);
        toEntityRecipeIngredient(ingredientList, result);
        tagService.createTagList(recipeDto.getTags(),result);


        return result.getId();

    }

    /* UPDATE (dirty checking 영속성 컨텍스트)
     *  User 객체를 영속화시키고, 영속화된 User 객체를 가져와 데이터를 변경하면
     * 트랜잭션이 끝날 때 자동으로 DB에 저장해준다. */
    @Transactional(readOnly = true)
    public RecipeEntity findById(Long id) {
        RecipeEntity recipe = recipeRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id: " + id));

        return recipe;
    }

    @Transactional
    public int updateView(Long id) {
        return recipeRepository.updateView(id);
    }

    /* Paging and Sort */
    @Transactional(readOnly = true)
    public Page<RecipeEntity> pageList(Pageable pageable) {
        return recipeRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Page<Tag> getCategories(Pageable pageable) {
        return tagRepository.findAll(pageable);
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

    //좋아요
    @Transactional
    public boolean saveLike(Long recipeID, String account){
        //아이디 검색
        UserEntity user = authRepository.findByAccount(account).orElseThrow(() ->
                new IllegalArgumentException("해당 아이다가 존재하지 않습니다. nickName: " + account));

        //좋아요 기록 검색
        Optional<RecipeLikeEntity> findLike = findLike(recipeID, user.getId());

        if(findLike.isEmpty()){
            RecipeEntity recipe = recipeRepository.findById(recipeID).orElseThrow(() ->
                    new IllegalArgumentException("해당 레시피가 존재하지 않습니다. recipeId: " + recipeID));

            RecipeLikeEntity recipeLikeEntity = RecipeLikeEntity.builder()
                    .recipe(recipe)
                    .user(user)
                    .build();

            recipeLikeRepository.save(recipeLikeEntity);
            recipeRepository.plusLike(recipeID);
            return true;
        }else{
            recipeLikeRepository.deleteByRecipe_IdAndUser_Id(recipeID, user.getId());
            recipeRepository.minusLike(recipeID);
            return false;
        }
    }

    public Optional<RecipeLikeEntity> findLike(Long recipeID, Long userID){
        return recipeLikeRepository.findByRecipe_IdAndUser_Id(recipeID, userID);
    }

    public List<RecipeEntity> searchInfo(String searchText){
        List<RecipeEntity> store = recipeRepository.findByTitleContaining(searchText);

        System.out.println(store.get(0).getTitle());
        return store;
    }

    public List<RecipeEntity> recomendInfo() {
        List<RecipeEntity> store = recipeRepository.findAll();
        List<RecipeEntity> resultRecipe = new ArrayList<RecipeEntity>();
        store.sort(new RecipeLikeComparator().reversed());
        int store_length = store.size();

        if(store_length>=6){
            store_length = 6;
        }

        for(int i=0;i<store_length;i++){
            resultRecipe.add(store.get(i));
        }

        return resultRecipe;
    }

//    public List<BoardEntity> topBoardInfo() {
//        List<BoardEntity> store = boardRepository.findAll();
//        List<BoardEntity> resultBoard = new ArrayList<BoardEntity>();
//        store.sort(new BoardLikeComparator().reversed());
//        int store_length = store.size();
//
//        if(store_length>=6){
//            store_length = 6;
//        }
//
//        for(int i=0;i<store_length;i++){
//            resultBoard.add(store.get(i));
//        }
//
//        return resultBoard;
//    }
//<<<<<<< HEAD


}

//class BoardLikeComparator implements Comparator<Board> {
//    @Override
//    public int compare(Board b1, Board b2) {
//        if (b1.getLikes() > b2.getLikes()) {
//            return 1;
//        } else if (b1.getLikes() < b2.getLikes()) {
//            return -1;
//        }
//        return 0;
//    }
//}
//
//class RecipeLikeComparator implements Comparator<RecipeEntity> {
//    @Override
//    public int compare(RecipeEntity r1, RecipeEntity r2) {
//        if (r1.getLikes() > r2.getLikes()) {
//            return 1;
//        } else if (r1.getLikes() < r2.getLikes()) {
//            return -1;
//        }
//        return 0;
//    }
//}