package com.teamz.recipe.Dto;

import com.teamz.recipe.domain.CommentEntity;
import com.teamz.recipe.domain.RecipeEntity;
import com.teamz.recipe.domain.User;
import com.teamz.recipe.domain.UserEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommentDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request{
        private Long id;
        private String comment; // 댓글 내용
        private String createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
        private String modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));;
        private RecipeEntity recipe;
        private UserEntity user; // 작성자
        /* Dto -> Entity */
        public CommentEntity toEntity(){
            CommentEntity comments = CommentEntity.builder()
                    .id(id)
                    .comment(comment)
                    .createdDate(createdDate)
                    .modifiedDate(modifiedDate)
                    .recipe(recipe)
                    .user(user)
                    .build();

            return comments;
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
        private final String comment; // 댓글 내용
        private final String createdDate;
        private final String modifiedDate;
        private final Long recipe_id;
        private final Long user_id; // 작성자

        /* Entity -> Dto*/
        public Response(CommentEntity comments) {
            this.id = comments.getId();
            this.comment = comments.getComment();
            this.createdDate = comments.getCreatedDate();
            this.modifiedDate = comments.getModifiedDate();
            this.recipe_id = comments.getRecipe().getId();
            this.user_id = comments.getUser().getId();
        }
    }

}
