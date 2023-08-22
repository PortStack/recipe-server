package com.teamz.recipe.Dto;

import com.teamz.recipe.domain.BoardEntity;
import com.teamz.recipe.domain.UserEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BoardDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request{
        private Long id;
        private String title;
        private String tag;
        private String writer;
        private String content;
        private int views;
        private int likes;
        private String createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
        private String modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));;
        private String boardThumbImg;

//        public BoardEntity toEntity(){
//            BoardEntity boards = BoardEntity.builder()
//                    .id(id)
//                    .title(title)
//                    .tag(tag)
//                    .writer(writer)
//                    .content(content)
//                    .views(views)
//                    .likes(likes)
//                    .createdDate(createdDate)
//                    .modifiedDate(modifiedDate)
//                    .boardThumbImg(boardThumbImg)
//                    .build();
//            return boards;
//        }
    }
    @RequiredArgsConstructor
    @Getter
    public static class Response {
        private Long id;
        private String title;
        private String tag;
        private String nickname;
        private String content;
        private int views;
        private int likes;
        private final LocalDateTime createdDate, modifiedDate;
        private String boardThumbImg;

        public Response(BoardEntity boards){
            this.id = boards.getId();
            this.title = boards.getTitle();
            this.tag = boards.getTag();
            this.nickname = boards.getUser().getNickname();
            this.content = boards.getContent();
            this.views = boards.getViews();
            this.likes = boards.getLikes();
            this.createdDate = boards.getCreatedDate();
            this.modifiedDate = boards.getModifiedDate();
            this.boardThumbImg = boards.getBoardThumbImg();
        }
    }
}
