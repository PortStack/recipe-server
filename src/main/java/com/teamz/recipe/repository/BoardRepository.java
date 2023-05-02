package com.teamz.recipe.repository;

import com.teamz.recipe.domain.Board;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {
    Board saveBoard(Board board);
    Board updateBoard(Board board);
    Board delectBoard(Board board);
    Optional<Board> bulletinBoardDetails(int bno);
    List<Board> findTenBoard(int pageNum); // 10개의 게시글 표시
    List<Board> findAll();
    Optional<Board> findBoardTitle(String title);
    Optional<Board> findBoardWritter(String writter);
    int storeSize();

}
