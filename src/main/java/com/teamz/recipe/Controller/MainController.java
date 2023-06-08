package com.teamz.recipe.Controller;

import com.teamz.recipe.domain.Board;
import com.teamz.recipe.domain.RecipeEntity;
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

    @PostMapping("/search")
    public ResponseEntity<Optional<RecipeEntity>> search(@RequestParam String searchText) {
        Optional<RecipeEntity> searchRecipe = recipeService.searchInfo(searchText);
        return new ResponseEntity<>(searchRecipe, HttpStatus.OK);
    }

    @PostMapping("/recomend")
    public ResponseEntity<List<RecipeEntity>> recomendRecipe() {
        List<RecipeEntity> recomendRecipe = recipeService.recomendInfo();
        return new ResponseEntity<>(recomendRecipe, HttpStatus.OK);
    }

    @PostMapping("/topBoard")
    public List<Board> topBoard() {
        List<Board> topBoard = recipeService.topBoardInfo();
        return topBoard;
    }
}
