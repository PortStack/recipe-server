package com.teamz.recipe.domain;

public class Recipe {
    //레시피 사진, 레시피 이름, 작성일자, 수정일자, 좋아요수

    private int rno;
    private String recipeImg;

    private String recipeName;

    private String createDate;

    private String revisionDate;

    private int likeCount;

    public String getRecipeImg() { return recipeImg; }

    public void setRecipeImg(String recipeImg) {
        this.recipeImg = recipeImg;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(String revisionDate) {
        this.revisionDate = revisionDate;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }
}
