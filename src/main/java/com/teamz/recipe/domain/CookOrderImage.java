package com.teamz.recipe.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name="COOK_ORDER_IMAGE")
@Entity
public class CookOrderImage{
    @Id
    @Column(name="COOK_ORDER_IMAGE_ID")
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private String originFileName;

    @Column(nullable = false)
    private String fullPath;

    @OneToOne(mappedBy = "cookOrderImage")
    private CookOrder cookOrder;

    @Column(nullable = false)
    private Long fileSize;
}
