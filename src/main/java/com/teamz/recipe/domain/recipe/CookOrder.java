package com.teamz.recipe.domain.recipe;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name="COOK_ORDER")
@Entity
public class CookOrder {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private int sequence;

    @Column(nullable = false)
    private String content;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "COOK_ORDER_IMAGE_ID")
    private CookOrderImage cookOrderImage;

    @JsonIgnore
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
