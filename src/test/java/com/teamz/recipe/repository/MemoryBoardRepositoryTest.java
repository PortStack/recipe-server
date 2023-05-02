package com.teamz.recipe.repository;

import com.teamz.recipe.domain.Board;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemoryBoardRepositoryTest {
    MemoryBoardRepository repository = new MemoryBoardRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void 게시글_저장(){
        Board board = new Board();

        board.setTitle("test");
        board.setBno(0);
        board.setContent("test중 입니다");

        repository.saveBoard(board);

        Board result = repository.findBoardTitle(board.getTitle()).get();
        Assertions.assertThat(board).isEqualTo(result);

    }

    @Test
    public void 게시글_작성자_검색(){
        Board board = new Board();

        board.setTitle("test");
        board.setBno(0);
        board.setContent("test중 입니다");
        board.setWritter("testMan");

        repository.saveBoard(board);

        Board result = repository.findBoardWritter(board.getWritter()).get();
        Assertions.assertThat(board).isEqualTo(result);

    }

    @Test
    public void 게시글_10개_조회(){
        for(int i=0;i<11;i++){
            Board board1 = new Board();
            board1.setBno(i);
            board1.setTitle(Integer.toString(i));
            repository.saveBoard(board1);
        }

        List<Board> result = repository.findTenBoard(0);
        Assertions.assertThat(result.size()).isEqualTo(10);


    }

    @Test
    public void 게시글_11개인_경우_2페이지_조회(){
        for(int i=0;i<13;i++){
            Board board1 = new Board();
            board1.setBno(i);
            board1.setTitle(Integer.toString(i));
            repository.saveBoard(board1);
        }

        List<Board> result = repository.findTenBoard(1);
        Assertions.assertThat(result.size()).isEqualTo(4);


    }

}