package com.teamz.recipe.Controller;


import com.teamz.recipe.Dto.RecipeDto;
import com.teamz.recipe.Dto.TagDto;
import com.teamz.recipe.domain.recipe.RecipeEntity;
import com.teamz.recipe.domain.UserEntity;
import com.teamz.recipe.domain.recipe.Tag;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/recipe")
@RequiredArgsConstructor
@RestController
public class RecipeController {
    private final RecipeService recipeService;
    private final AuthService authService;

    @PostMapping("/new")
    public ResponseEntity save(RecipeDto.Request dto,
                               @RequestPart("orderImage") List<MultipartFile> orderImage,
                               @RequestPart("themNail") List<MultipartFile> themNail,
                               @AuthenticationPrincipal UserDetails userDetails) throws Exception {
        dto.setOrderImage(orderImage);
        dto.setThemNail(themNail);

        return ResponseEntity.ok(recipeService.save(dto));
    }

    @GetMapping("/read/{id}")
    public ResponseEntity read(@PathVariable Long id,@RequestParam(value = "nickname" , required = false, defaultValue="noLogin") String nickname) throws Exception {
        boolean likeState = false;
        System.out.println("readTest");
        recipeService.updateView(id);
        RecipeEntity recipeEntity = recipeService.findById(id);

        if(!nickname.equals("noLogin")){
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

    @GetMapping("/category")
    public ResponseEntity category(@PageableDefault(size=10, sort="id", direction = Sort.Direction.DESC) Pageable pageable){
        Page<Tag> tags = recipeService.getCategories(pageable);
        return ResponseEntity.ok(tags);
    }

    @DeleteMapping("/delete/{idx}")
    public ResponseEntity delete(@PathVariable Long idx,@AuthenticationPrincipal UserDetails userDetails){
        System.out.println(idx);
        boolean result = recipeService.delete(idx,userDetails.getUsername());
        return ResponseEntity.ok(result);
    }
}
