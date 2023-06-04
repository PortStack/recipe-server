package com.teamz.recipe.Controller;


import com.teamz.recipe.Dto.RecipeDto;
import com.teamz.recipe.domain.RecipeEntity;
import com.teamz.recipe.domain.RecipeLikeEntity;
import com.teamz.recipe.domain.UserEntity;
import com.teamz.recipe.service.AuthService;
import com.teamz.recipe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/recipe")
@RequiredArgsConstructor
@RestController
public class RecipeController {
    private final RecipeService recipeService;
    private final AuthService authService;

    @PostMapping("/new")
    public ResponseEntity save(RecipeDto.Request dto, @AuthenticationPrincipal UserDetails userDetails) throws Exception {
//        return ResponseEntity.ok(recipeService.save(dto, user.getNickname()));
        System.out.println(userDetails.getUsername());
        return ResponseEntity.ok(recipeService.save(dto));
    }

    @GetMapping("/read/{id}")
    public ResponseEntity read(@PathVariable Long id,@RequestParam(value = "nickname" , required = false, defaultValue="noLogin") String nickname) throws Exception {
        boolean likeState = false;

        recipeService.updateView(id);
        RecipeEntity recipeEntity = recipeService.findById(id);

        if(!nickname.equals("noLogin")){
            System.out.println(nickname);
            UserEntity userEntity = authService.findByNickname(nickname);
            if(!recipeService.findLike(id,userEntity.getId()).isEmpty()){
                likeState = true;
            }

        }

        RecipeDto.Response responseDto = new RecipeDto.Response(recipeEntity, likeState);

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity retrieveRecipes(@PageableDefault(size=10, sort="id", direction = Sort.Direction.DESC) Pageable pageable){
        Page<RecipeEntity> posts = recipeService.pageList(pageable);
        return ResponseEntity.ok(posts.map(m -> new RecipeDto.Response(m,false)));
    }

    @PostMapping("/test")
    public String test(RecipeDto.Request dto
    ){
        System.out.println(dto.getOrderImage().get(0).getOriginalFilename());
        System.out.println(dto.getThemNail().get(0).getOriginalFilename());
        System.out.println(dto.getCookOrders().get(0).getContent());
        return dto.getWriter();
    }

    @GetMapping("/like/{idx}")
    public ResponseEntity like(@PathVariable Long idx,@AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(recipeService.saveLike(idx,userDetails.getUsername()));
    }

}
