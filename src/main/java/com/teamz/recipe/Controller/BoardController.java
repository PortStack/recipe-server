package com.teamz.recipe.Controller;

import com.teamz.recipe.domain.Board;
import com.teamz.recipe.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @ResponseBody
    @PostMapping("/boards/new")
    public String writeBoard(@RequestBody Board board){
        int boardBno = boardService.writeBoard(board);
        return Integer.toString(boardBno);
    }

    @ResponseBody
    @PostMapping("/boards/update")
    public boolean updateBoard(@RequestBody Board board){
        boolean boardBno = boardService.updateBoard(board);
        return boardBno;
    }


    @GetMapping("/boards/lookupPageBoard")
    @ResponseBody
    public List<Board> lookupPagePost(@RequestParam int pageNum){
        List<Board> boardList = boardService.getPageBoardList(pageNum);
        return boardList;
    }

    @ResponseBody
    @PostMapping("/boards/search/title")
    public List<Board> searchBoardByTitle(@RequestParam String title){
        List<Board> boardList = boardService.searchBoardByTitle(title);
        return boardList;
    }

    @ResponseBody
    @PostMapping("/boards/search/writter")
    public List<Board> searchBoardByWritter(@RequestParam String writter){
        List<Board> boardList = boardService.searchBoardByWritter(writter);
        return boardList;
    }

    @ResponseBody
    @PostMapping("/boards/boardDetail")
    public Board bulletinBoardDetail(@RequestBody Board board){
        Board boardList = boardService.bulletinBoardDetail(board);
        return boardList;
    }
}
