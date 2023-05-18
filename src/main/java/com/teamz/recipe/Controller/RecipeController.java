package com.teamz.recipe.Controller;


import com.teamz.recipe.Dto.RecipeDto;
import com.teamz.recipe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/recipe")
@RequiredArgsConstructor
@RestController
public class RecipeController {
    private final RecipeService recipeService;

    @PostMapping("/new")
    public ResponseEntity save(RecipeDto.Request dto) throws Exception {
//        return ResponseEntity.ok(recipeService.save(dto, user.getNickname()));
        return ResponseEntity.ok(recipeService.save(dto));
    }

    @PostMapping("/test")
    public String test(RecipeDto.Request dto
    ){
        System.out.println(dto.getOrderImage().get(0).getOriginalFilename());
        System.out.println(dto.getThemNail().get(0).getOriginalFilename());
        System.out.println(dto.getCookOrders().get(0).getContent());
        return dto.getWriter();
    }


}
