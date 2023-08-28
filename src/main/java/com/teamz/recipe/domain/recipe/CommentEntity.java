package com.teamz.recipe.domain.recipe;

import com.teamz.recipe.domain.UserEntity;
import com.teamz.recipe.domain.recipe.RecipeEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import static jakarta.persistence.FetchType.LAZY;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name="COMMENTS")
@Entity
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String comment; // 댓글 내용

    @Column(name = "created_date")
    @CreatedDate
    private String createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    private String modifiedDate;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_id")
    private CommentEntity parent;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "RECIPE_ID")
    private RecipeEntity recipe;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user; // 작성자

    public void updateComment(String comment){
        this.comment = comment;
    }

}

//    private Long id; // 댓글 id
//    private String bno; //댓글이 속한 게시글 번호
//    private String cno; //댓글 번호
//    private String writer; //댓글 작성자
//    private String comment; //댓글 내용
//    @Column(name = "created_date")
//    @CreatedDate
//    private String createdDate;
//
//    @Column(name = "modified_date")
//    @LastModifiedDate
//    private String modifiedDate;
//    private int cDepth; //댓글 깊이
//    private int cGroup; //댓글 그룹
