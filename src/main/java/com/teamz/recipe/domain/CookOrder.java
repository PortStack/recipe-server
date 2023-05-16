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
@Table(name="COOK_ORDER")
@Entity
public class CookOrder {
    @Id
    @Column(name="COOK_ORDER_ID")
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long Id;

    private int order;

    private String content;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "COOK_ORDER_IMAGE_ID")
    private CookOrderImage cookOrderImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RECIPE_ID")
    private RecipeEntity recipe;

    public void setRecipe(RecipeEntity recipe){
        this.recipe = recipe;

        if(recipe.getCookOrders().contains(this)){
            recipe.getCookOrders().add(this);
        }
    }

}
