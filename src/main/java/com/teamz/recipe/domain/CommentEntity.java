package com.teamz.recipe.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

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

    @ManyToOne
    @JoinColumn(name = "RECIPE_ID")
    private RecipeEntity recipe;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // 작성자

    public void update(String comment){
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
