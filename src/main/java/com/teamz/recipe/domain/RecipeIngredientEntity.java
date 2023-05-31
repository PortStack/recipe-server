package com.teamz.recipe.domain;


import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name="RECIPE_INGREDIENT")
@Entity
public class RecipeIngredientEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int count;

    @Column(nullable = false)
    private String unit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INGREDIENT_ID")
    private Ingredient ingredient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RECIPE_ID")
    private RecipeEntity recipe;

    public void setRecipe(RecipeEntity recipe){
        this.recipe = recipe;

        if(recipe.getRecipeIngredients().contains(this)){
            recipe.getRecipeIngredients().add(this);
        }
    }
}
