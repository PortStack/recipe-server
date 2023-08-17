package com.teamz.recipe.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private UserEntity user;

    @JsonIgnore
    @OneToMany(mappedBy = "recipe", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("id asc") // 댓글 정렬
    private List<RecipeIngredientEntity> recipeIngredients = new ArrayList<RecipeIngredientEntity>();

    @JsonIgnore
    @OneToMany(mappedBy = "recipe", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("id asc") // 댓글 정렬
    private List<CommentEntity> comments;

    @JsonIgnore
    @OneToMany(mappedBy = "recipe", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("id asc")
    private List<CookOrder> cookOrders;

    @JsonIgnore
    @OneToMany(mappedBy = "recipe", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("id asc")
    private List<ThumbNailEntity> themNails;

    @JsonIgnore
    @OneToMany(mappedBy = "recipe", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("id asc")
    private List<RecipeTagMap> recipeTagMaps;

//    public void addCookOrders(CookOrder cookOrder){
//        this.cookOrders.add(cookOrder);
//
//        if(cookOrder.getRecipe() != this){
//            cookOrder.setRecipe(this);
//        }
//    }
//
//    public void addRecipeIngredients(RecipeIngredientEntity recipeIngredient){
//        this.recipeIngredients.add(recipeIngredient);
//
//        if(recipeIngredient.getRecipe() != this){
//            recipeIngredient.setRecipe(this);
//        }
//    }

}
