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
@Table(name="recipe")
@Entity
public class RecipeIngredientEntity {
    @Id
    @Column(name="RECIPE_INGREDIENT_ID")
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int title;

    @Column(nullable = false)
    private String unit;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int views;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "INGREDIENT_ID")
    private Ingredient ingredient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RECIPE_ID")
    private RecipeEntity recipe;
}
