package com.teamz.recipe.Dto;

import com.teamz.recipe.domain.recipe.CookOrder;
import lombok.*;

public class CookOrderDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Getter
    public static class Request {
        private Long Id;
        private int sequence;
        private String content;
        private String orderImageName;

        public CookOrder toEntity(){
            CookOrder cookOrder = CookOrder.builder()
                    .Id(Id)
                    .sequence(sequence)
                    .content(content)
                    .build();

            return cookOrder;
        }


    }

    @RequiredArgsConstructor
    @Getter
    public static class Response {
        private final Long id;
        private final int sequence;
        private final String content;
        private final String imageUrl;

        public Response(CookOrder cookOrder){
            this.id = cookOrder.getId();
            this.sequence = cookOrder.getSequence();
            this.content = cookOrder.getContent();
            this.imageUrl = cookOrder.getCookOrderImage().getFullPath();
        }

    }
}
