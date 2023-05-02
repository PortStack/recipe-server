package com.teamz.recipe.service;

import com.teamz.recipe.domain.Board;
import com.teamz.recipe.modules.CurrentDateTime;
import com.teamz.recipe.repository.BoardRepository;

import java.util.List;
import java.util.Optional;

public class BoardService {

    private final BoardRepository boardRepository;
    public static CurrentDateTime currentDateTime = new CurrentDateTime();

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    /*
    * 새 게시글 셋팅
    * */
    private Board setNewBoard(Board board){
        int lastIdx = boardRepository.storeSize();
        String now = currentDateTime.getCurrentDateTime();
        board.setBno(lastIdx);
        board.setCreateDate(now);
        board.setModifiedDate(now);
        return board;
    }

    /*
    * 게시글 존재 유무 검색
    * */
    private void isBoard(Optional<Board> board){
        if (board.isEmpty()){
            throw new IllegalStateException("게시글이 존재하지 않습니다");
        }
    }

    /*
    *  게시글 생성
    * */
    public int writeBoard(Board board){
        Board newBoard = setNewBoard(board);
        boardRepository.saveBoard(newBoard);
        return newBoard.getBno();
    }

    /*
    * 게시글 수정
    * */
    public boolean updateBoard(Board board){
        Optional<Board> preBoard = boardRepository.bulletinBoardDetails(board.getBno());
        isBoard(preBoard);
        boardRepository.updateBoard(board);
        return true;
    }

    /*
    * 게시글 상세정보 보기
    * */
    public Board bulletinBoardDetail(Board board){
        Optional<Board> boardDetail = boardRepository.bulletinBoardDetails(board.getBno());
        isBoard(boardDetail);
        return boardDetail.get();
    }

    public List<Board> searchBoardByTitle(String title){
        Optional<Board> boardList = boardRepository.findBoardTitle(title);
        isBoard(boardList);
        return boardList.stream().toList();
    }

    public List<Board> searchBoardByWritter(String writter){
        Optional<Board> boardList = boardRepository.findBoardTitle(writter);
        isBoard(boardList);
        return boardList.stream().toList();
    }

    public List<Board> getPageBoardList(int pageNum){
        return boardRepository.findTenBoard(pageNum);
    }
}
