package com.teamz.recipe.Controller;

import com.teamz.recipe.Dto.RecipeDto;
import com.teamz.recipe.domain.Board;
import com.teamz.recipe.domain.recipe.RecipeEntity;
//import com.teamz.recipe.domain.BoardEntity;
//import com.teamz.recipe.domain.RecipeEntity;
//import com.teamz.recipe.domain.UserEntity;
//import com.teamz.recipe.service.AuthService;
import com.teamz.recipe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/main")
@RequiredArgsConstructor
@RestController
public class MainController {

    private final RecipeService recipeService;

    @GetMapping("/search")
    public ResponseEntity<Page<RecipeDto.Response>> search(@RequestParam String searchText,
                                                     @RequestParam(value = "nickname" , required = false, defaultValue="noLogin") String nickname,
                                                     @PageableDefault(size=10, sort="id", direction = Sort.Direction.DESC) Pageable pageable) throws Exception {
        System.out.println(searchText);
        Page<RecipeEntity> searchRecipe = recipeService.searchInfo(searchText,pageable);
        return ResponseEntity.ok(searchRecipe.map(m -> new RecipeDto.Response(m,false)));
    }

    @GetMapping("/recomend")
    public ResponseEntity<List<RecipeEntity>> recomendRecipe() {
        List<RecipeEntity> recomendRecipe = recipeService.recomendInfo();
        return new ResponseEntity<>(recomendRecipe, HttpStatus.OK);
    }

//    @GetMapping("/topBoard")
//    public List<BoardEntity> topBoard() {
//        List<BoardEntity> topBoard = recipeService.topBoardInfo();
//        return topBoard;
//    }
}
