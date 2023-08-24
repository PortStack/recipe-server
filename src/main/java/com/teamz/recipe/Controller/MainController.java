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
    public ResponseEntity<List<RecipeEntity>> search(@RequestParam String searchText, @RequestParam(value = "nickname" , required = false, defaultValue="noLogin") String nickname) throws Exception {
        System.out.println(searchText);
        List<RecipeEntity> searchRecipe = recipeService.searchInfo(searchText);
        return new ResponseEntity<>(searchRecipe, HttpStatus.OK);
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
