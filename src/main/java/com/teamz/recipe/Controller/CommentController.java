package com.teamz.recipe.Controller;

import com.teamz.recipe.Dto.CommentDto;
import com.teamz.recipe.Dto.RecipeDto;
import com.teamz.recipe.domain.UserEntity;
import com.teamz.recipe.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/recipe/comment")
@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    /* CREATE */
    @PostMapping("/{recipeId}/new")
    public ResponseEntity save(@PathVariable Long recipeId, @RequestBody CommentDto.Request dto, @AuthenticationPrincipal UserDetails userDetails){
        System.out.println(recipeId);
        System.out.println((userDetails.getUsername()));
        System.out.println(dto.getComment());
        return ResponseEntity.ok(commentService.save(recipeId,userDetails.getUsername(),dto));
    }

    /* READ */
    @GetMapping("/{recipeId}/read")
    public List<CommentDto.Response> read(@PathVariable Long recipeId) {
        return commentService.findAll(recipeId);
    }

    /* UPDATE */
    @PutMapping({"/{recipeId}/update/{id}"})
    public ResponseEntity<Long> update(@PathVariable Long recipeId, @PathVariable Long id, @RequestBody CommentDto.Request dto) {
        System.out.println(recipeId);
        System.out.println(dto.getComment());
        commentService.update(recipeId, id, dto);
        return ResponseEntity.ok(id);
    }

    /* DELETE */
    @DeleteMapping("/{recipeId}/delete/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long recipesId, @PathVariable Long id) {
        commentService.delete(recipesId, id);
        return ResponseEntity.ok(id);
    }
}
