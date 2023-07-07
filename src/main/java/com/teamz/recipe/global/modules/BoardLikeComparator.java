package com.teamz.recipe.global.modules;


import com.teamz.recipe.domain.BoardEntity;

import java.util.Comparator;

public class BoardLikeComparator implements Comparator<BoardEntity> {
    @Override
    public int compare(BoardEntity b1, BoardEntity b2) {
        if (b1.getLikes() > b2.getLikes()) {
            return 1;
        } else if (b1.getLikes() < b2.getLikes()) {
            return -1;
        }
        return 0;
    }
}