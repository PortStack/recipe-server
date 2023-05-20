package com.teamz.recipe.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    @Column(name="RECIPE_ID")
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(nullable = false)
    private String writer;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int views;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "USER_ID")
//    private User user;

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
