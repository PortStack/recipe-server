package com.teamz.recipe.repository;

import com.teamz.recipe.domain.Board;

import java.util.*;

public class MemoryBoardRepository implements BoardRepository{
    private static Map<Integer, Board> store = new HashMap<>();

    //새 글 추가
    @Override
    public Board saveBoard(Board board) {
        store.put(board.getBno(),board);
        return board;
    }

    //글 수정
    @Override
    public Board updateBoard(Board board) {
        store.put(board.getBno(),board);
        return board;
    }

    //글 삭제
    @Override
    public Board delectBoard(Board board) {
        store.remove(board.getBno());
        return board;
    }

    @Override
    public List<Board> findTenBoard(int pageNum) {
        List boardList = new ArrayList<Board>();
        int lastBoardIdx = store.size();
        System.out.println(lastBoardIdx);

        for(int i=0;i<10;i++){
            if(lastBoardIdx-(i + pageNum*10) <= 0){
                break;
            }

            boardList.add(store.get(lastBoardIdx-(i + 1 + pageNum*10)));
        }
        for(int i=0;i<boardList.size();i++){
            System.out.println(boardList.get(i));
        }
        return boardList;
    }

    @Override
    public Optional<Board> bulletinBoardDetails(int bno) {
        return store.values().stream()
                .filter(board -> board.getBno().equals(bno))
                .findAny();
    }

    @Override
    public Optional<Board> findBoardTitle(String title) {
        return store.values().stream()
                .filter(board -> board.getTitle().equals(title))
                .findAny();
    }

    @Override
    public Optional<Board> findBoardWritter(String writter) {
        List integers = new ArrayList<Board>();
        return store.values().stream()
                .filter(board -> board.getWritter().equals(writter))
                .findAny();
    }

    @Override
    public List<Board> findAll() {
        return new ArrayList<>(store.values());
    }

    public int storeSize(){
        return store.size();
    }

    public void clearStore() {
        store.clear();
    }

}
