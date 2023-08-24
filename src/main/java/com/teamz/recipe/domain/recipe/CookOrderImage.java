package com.teamz.recipe.domain.recipe;

import com.teamz.recipe.domain.recipe.CookOrder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name="COOK_ORDER_IMAGE")
@Entity
public class CookOrderImage{
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private String originFileName;

    @Column(nullable = false)
    private String fullPath;

    @JsonIgnore
    @OneToOne(mappedBy = "cookOrderImage")
    private CookOrder cookOrder;

    @Column(nullable = false)
    private Long fileSize;
}
