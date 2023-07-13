package com.teamz.recipe.Dto;

import com.teamz.recipe.domain.RecipeEntity;
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
        private String tags;
        private List<RecipeIngredientDto.Request> recipeIngredients = new ArrayList<>();
        private List<CookOrderDto.Request> cookOrders = new ArrayList<>();
        private List<MultipartFile> orderImage;
        private List<MultipartFile> themNail;

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
        private final String nickname;
        private final LocalDateTime createdDate, modifiedDate;
        private final int views;
        private final int likes;
        private final boolean likeState;
        private final List<CommentDto.Response> comments;
        private final List<RecipeIngredientDto.Response> ingredientList;
        private final List<CookOrderDto.Response> cookOrderList;

        public Response(RecipeEntity recipes,boolean likeState){
            this.id = recipes.getId();
            this.title = recipes.getTitle();
            this.nickname = recipes.getUser().getNickname();
            this.createdDate = recipes.getCreatedDate();
            this.modifiedDate = recipes.getModifiedDate();
            this.views = recipes.getViews();
            this.likes = recipes.getLikes();
            this.likeState = likeState;
            this.comments = recipes.getComments().stream().map(CommentDto.Response::new).collect(Collectors.toList());
            this.ingredientList = recipes.getRecipeIngredients().stream().map(RecipeIngredientDto.Response::new).collect(Collectors.toList());
            this.cookOrderList = recipes.getCookOrders().stream().map(CookOrderDto.Response::new).collect(Collectors.toList());
        }
    }
}
