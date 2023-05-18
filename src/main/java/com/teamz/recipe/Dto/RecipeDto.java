package com.teamz.recipe.Dto;

import com.teamz.recipe.domain.RecipeEntity;
import com.teamz.recipe.domain.User;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RecipeDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {
        private Long id;
        private String title;
        private String writer;
        private String views;
        private User user;
        private List<RecipeIngredientDto.Request> recipeIngredients = new ArrayList<>();
        private List<CookOrderDto.Request> cookOrders = new ArrayList<>();
        private List<MultipartFile> orderImage;
        private List<MultipartFile> themNail;

        /* Dto -> Entity */
        public RecipeEntity toEntity(){
            RecipeEntity recipe = RecipeEntity.builder()
                    .id(id)
                    .title(title)
                    .writer(writer)
                    .views(0)
                    .build();

            return recipe;
        }
    }

    /**
     * 댓글 정보를 리턴할 응답(Response) 클래스
     * Entity 클래스를 생성자 파라미터로 받아 데이터를 Dto로 변환하여 응답
     * 별도의 전달 객체를 활용해 연관관계를 맺은 엔티티간의 무한참조를 방지
     */
    @RequiredArgsConstructor
    @Getter
    public static class Response {
        private final Long id;
        private final String title;
        private final String writer;
        private final LocalDateTime createdDate, modifiedDate;
        private final int views;
        private final List<CommentDto.Response> comments;
        private final List<RecipeIngredientDto.Response> ingredientList;
//        private final Long userId;

        public Response(RecipeEntity recipes){
            this.id = recipes.getId();
            this.title = recipes.getTitle();
            this.writer = recipes.getWriter();
            this.createdDate = recipes.getCreatedDate();
            this.modifiedDate = recipes.getModifiedDate();
            this.views = recipes.getViews();
            this.comments = recipes.getComments().stream().map(CommentDto.Response::new).collect(Collectors.toList());
            this.ingredientList = recipes.getRecipeIngredients().stream().map(RecipeIngredientDto.Response::new).collect(Collectors.toList());
        }
    }
}
