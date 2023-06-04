package com.teamz.recipe.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name="RECIPES")
public class RecipeEntity extends TimeEntity{
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int views;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int likes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private UserEntity user;

    @OneToMany(mappedBy = "recipe", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("id asc") // 댓글 정렬
    private List<RecipeIngredientEntity> recipeIngredients = new ArrayList<RecipeIngredientEntity>();

    @OneToMany(mappedBy = "recipe", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("id asc") // 댓글 정렬
    private List<CommentEntity> comments;

    @OneToMany(mappedBy = "recipe", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("id asc")
    private List<CookOrder> cookOrders;

    @OneToMany(mappedBy = "recipe", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("id asc")
    private List<ThumbNailEntity> themNails;

    public void addCookOrders(CookOrder cookOrder){
        this.cookOrders.add(cookOrder);

        if(cookOrder.getRecipe() != this){
            cookOrder.setRecipe(this);
        }
    }

    public void addRecipeIngredients(RecipeIngredientEntity recipeIngredient){
        this.recipeIngredients.add(recipeIngredient);

        if(recipeIngredient.getRecipe() != this){
            recipeIngredient.setRecipe(this);
        }
    }

}
