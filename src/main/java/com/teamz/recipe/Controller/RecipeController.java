package com.teamz.recipe.Controller;


import com.teamz.recipe.Dto.RecipeDto;
import com.teamz.recipe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/recipe")
@RequiredArgsConstructor
@RestController
public class RecipeController {
    private final RecipeService recipeService;

    @PostMapping("/new")
    public ResponseEntity save(@RequestBody RecipeDto.Request dto) throws Exception {
//        return ResponseEntity.ok(recipeService.save(dto, user.getNickname()));
        return ResponseEntity.ok(recipeService.save(dto));
    }


}
