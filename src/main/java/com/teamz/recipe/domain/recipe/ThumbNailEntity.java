package com.teamz.recipe.domain.recipe;

import com.teamz.recipe.domain.recipe.RecipeEntity;
import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name="THUMBNAIL")
public class ThumbNailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String originFileName;

    @Column(nullable = false)
    private String fullPath;

    @Column(nullable = false)
    private Long fileSize;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RECIPE_ID")
    private RecipeEntity recipe;


}
