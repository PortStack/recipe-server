package com.teamz.recipe.Dto;

import com.teamz.recipe.domain.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BoardCommentDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request{
        private Long id;
        private String comment; // 댓글 내용
        private String createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
        private String modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));;
        private BoardEntity board;
        private UserEntity user; // 작성자
        /* Dto -> Entity */
        public BoardCommentEntity toEntity(){
            BoardCommentEntity comments = BoardCommentEntity.builder()
                    .id(id)
                    .comment(comment)
                    .createdDate(createdDate)
                    .modifiedDate(modifiedDate)
                    .board(board)
                    .user(user)
                    .build();

            return comments;
        }
    }

    @RequiredArgsConstructor
    @Getter
    public static class Response {
        private final Long id;
        private final String comment; // 댓글 내용
        private final String createdDate;
        private final String modifiedDate;
        private final Long board_id;
        private final String nickName; // 작성자

        /* Entity -> Dto*/
        public Response(BoardCommentEntity comments) {
            this.id = comments.getId();
            this.comment = comments.getComment();
            this.createdDate = comments.getCreatedDate();
            this.modifiedDate = comments.getModifiedDate();
            this.board_id = comments.getBoard().getId();
            this.nickName = comments.getUser().getNickname();
        }
    }
}
