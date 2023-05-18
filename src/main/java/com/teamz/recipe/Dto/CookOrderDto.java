package com.teamz.recipe.Dto;

import com.teamz.recipe.domain.CookOrder;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

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
}
