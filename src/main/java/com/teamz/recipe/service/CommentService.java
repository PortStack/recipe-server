package com.teamz.recipe.service;

import com.teamz.recipe.Dto.CommentDto;
import com.teamz.recipe.domain.CommentEntity;
import com.teamz.recipe.domain.RecipeEntity;
import com.teamz.recipe.domain.UserEntity;
import com.teamz.recipe.repository.AuthRepository;
import com.teamz.recipe.repository.CommentRepository;
import com.teamz.recipe.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final AuthRepository authRepository;
    private final RecipeRepository recipeRepository;

    /* CREATE */
    @Transactional
    public Long save(Long recipeId, String account, CommentDto.Request dto){
        UserEntity user = authRepository.findByAccount(account).orElseThrow(() ->
                new IllegalArgumentException("댓글 쓰기 실패: 해당 유저가 존재하지 않는다"));

        RecipeEntity recipe = recipeRepository.findById(recipeId).orElseThrow(() ->
                new IllegalArgumentException("댓글 쓰기 실패: 해당 레시피가 존재하지 않는다"));

        dto.setUser(user);
        dto.setRecipe(recipe);

        CommentEntity commentEntity = dto.toEntity();
        commentRepository.save(commentEntity);

        return commentEntity.getId();
    }

    /* READ */
    @Transactional(readOnly = true)
    public List<CommentDto.Response> findAll(Long id) {
        RecipeEntity posts = recipeRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 레시피가 존재하지 않습니다. id: " + id));

        List<CommentEntity> comments = posts.getComments();

        return comments.stream().map(CommentDto.Response::new).collect(Collectors.toList());
    }

    /* UPDATE */
    @Transactional
    public void update(Long recipeId, Long id, CommentDto.Request dto) {
        CommentEntity comment = commentRepository.findByRecipe_IdAndId(recipeId, id).orElseThrow(() ->
                new IllegalArgumentException("해당 댓글이 존재하지 않습니다. " + id));

        comment.update(dto.getComment());
    }

    /* DELETE */
    @Transactional
    public void delete(Long recipesId, Long id) {
        CommentEntity comment = commentRepository.findByRecipe_IdAndId(recipesId, id).orElseThrow(() ->
                new IllegalArgumentException("해당 댓글이 존재하지 않습니다. id=" + id));

        commentRepository.delete(comment);
    }

}
