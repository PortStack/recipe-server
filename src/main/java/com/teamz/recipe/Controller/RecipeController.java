package com.teamz.recipe.Controller;


import com.teamz.recipe.Dto.RecipeDto;
import com.teamz.recipe.domain.RecipeEntity;
import com.teamz.recipe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/read/{id}")
    public ResponseEntity read(@PathVariable Long id){
        recipeService.updateView(id);
        return ResponseEntity.ok(recipeService.findById(id));
    }

    @GetMapping
    public ResponseEntity retrieveRecipes(@PageableDefault(size=10, sort="id", direction = Sort.Direction.DESC) Pageable pageable){
        Page<RecipeEntity> posts = recipeService.pageList(pageable);
        return ResponseEntity.ok(posts.map(m -> new RecipeDto.Response(m)));
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
