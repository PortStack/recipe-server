package com.teamz.recipe.domain;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.FetchType.LAZY;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "RecipeTagMap")
public class RecipeTagMap extends TimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "recipe_id")
    private RecipeEntity recipeEntity;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "tag_id")
    private Tag tag;
}
