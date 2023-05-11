package com.teamz.recipe.Controller;

import com.teamz.recipe.domain.Board;
import com.teamz.recipe.domain.Recipe;
import com.teamz.recipe.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public class MainController {

    private final MainService mainService;

    @Autowired
    public MainController(MainService mainService) {this.mainService = mainService;}

    @PostMapping("/main/search")
    @ResponseBody
    public Optional<Recipe> search(@RequestParam String searchText) {
        Optional<Recipe> searchRecipe = mainService.searchInfo(searchText);
        return searchRecipe;
    }

    @PostMapping("/main/recomend")
    @ResponseBody
    public List<Recipe> recomendRecipe() {
        List<Recipe> recomendRecipe = mainService.recomendInfo();
        return recomendRecipe;
    }

    @PostMapping("/main/topBoard")
    @ResponseBody
    public List<Board> topBoard() {
        List<Board> topBoard = mainService.topBoardInfo();
        return topBoard;
    }
}
