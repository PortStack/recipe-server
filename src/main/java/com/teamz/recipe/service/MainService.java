package com.teamz.recipe.service;

import com.teamz.recipe.domain.Board;
import com.teamz.recipe.repository.BoardRepository;
import com.teamz.recipe.repository.MainRepository;
import com.teamz.recipe.domain.Recipe;
import com.teamz.recipe.repository.MemoryBoardRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class MainService {

    private final MainRepository mainRepository;

    private final BoardRepository boardRepository = new MemoryBoardRepository();


    public MainService(MainRepository mainRepository) { this.mainRepository = mainRepository; }

    public Optional<Recipe> searchInfo(String searchText){
        List<Recipe> store = mainRepository.getRecipe();

        return store.stream()
                .filter(recipe -> recipe.getRecipeName().contains(searchText))
                .findAny();
    }

    public List<Recipe> recomendInfo() {
        List<Recipe> store = mainRepository.getRecipe();
        List<Recipe> resultRecipe = new ArrayList<Recipe>();
        store.sort(new RecipeLikeComparator().reversed());

        for(int i=0;i<6;i++){
            resultRecipe.add(store.get(i));
        }

        return resultRecipe;
    }

    public List<Board> topBoardInfo() {
        List<Board> store = boardRepository.findAll();
        List<Board> resultBoard = new ArrayList<Board>();
        store.sort(new BoardLikeComparator().reversed());

        for(int i=0;i<4;i++){
            resultBoard.add(store.get(i));
        }

        return resultBoard;
    }

}

class BoardLikeComparator implements Comparator<Board> {
    @Override
    public int compare(Board b1, Board b2) {
        if (b1.getLikes() > b2.getLikes()) {
            return 1;
        } else if (b1.getLikes() < b2.getLikes()) {
            return -1;
        }
        return 0;
    }
}

class RecipeLikeComparator implements Comparator<Recipe> {
    @Override
    public int compare(Recipe r1, Recipe r2) {
        if (r1.getLikeCount() > r2.getLikeCount()) {
            return 1;
        } else if (r1.getLikeCount() < r2.getLikeCount()) {
            return -1;
        }
        return 0;
    }
}
