package com.teamz.recipe.Controller;

import com.teamz.recipe.Dto.RecipeDto;
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
    public ResponseEntity search(@RequestParam String searchText) {
        List<RecipeEntity> searchRecipe = recipeService.searchInfo(searchText);
        return ResponseEntity.ok(searchRecipe.stream().map(m -> new RecipeDto.Response(m,false)));
    }

    @GetMapping("/recomend")
    public ResponseEntity<List<RecipeEntity>> recomendRecipe() {
        List<RecipeEntity> recomendRecipe = recipeService.recomendInfo();
        return new ResponseEntity<>(recomendRecipe, HttpStatus.OK);
    }

    @GetMapping("/topBoard")
    public List<Board> topBoard() {
        List<Board> topBoard = recipeService.topBoardInfo();
        return topBoard;
    }
}
