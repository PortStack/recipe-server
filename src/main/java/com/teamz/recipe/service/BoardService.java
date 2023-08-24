package com.teamz.recipe.service;

import com.sun.tools.jconsole.JConsoleContext;
import com.teamz.recipe.Dto.BoardDto;
import com.teamz.recipe.domain.*;
import com.teamz.recipe.repository.AuthRepository;
import com.teamz.recipe.repository.BoardLikeRepository;
import com.teamz.recipe.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardLikeRepository boardLikeRepository;
    private final AuthRepository authRepository;
    /*
    * 새 게시글 셋팅
    * */
    public Long save(BoardDto.Request boardDto) throws Exception {
        UserEntity user = authRepository.findByNickname(boardDto.getWriter()).orElseThrow(() ->
                new IllegalArgumentException("해당 유저가 존재하지 않습니다" + boardDto.getWriter()));

        System.out.println("content: " + boardDto.getContent());

        BoardEntity board = BoardEntity.builder()
                .title(boardDto.getTitle())
                .tag(boardDto.getTag())
                .user(user)
                .content(boardDto.getContent())
                .views(0)
                .likes(0)
                .boardThumbImg(boardDto.getBoardThumbImg())
                .build();

        BoardEntity result = boardRepository.save(board);

        return result.getId();
    }

    @Transactional(readOnly = true)
    public BoardEntity findById(Long id) {
        BoardEntity board = boardRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id: " + id));

        return board;
    }

    @Transactional
    public int updateView(Long id) {
        return boardRepository.updateView(id);
    }

    @Transactional(readOnly = true)
    public Page<BoardEntity> pageList(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    @Transactional
    public boolean saveLike(Long boardID, String account){
        //아이디 검색
        UserEntity user = authRepository.findByAccount(account).orElseThrow(() ->
                new IllegalArgumentException("해당 아이다가 존재하지 않습니다. nickName: " + account));

        //좋아요 기록 검색
        Optional<BoardLikeEntity> findLike = findLike(boardID, user.getId());

        if(findLike.isEmpty()){
            BoardEntity board = boardRepository.findById(boardID).orElseThrow(() ->
                    new IllegalArgumentException("해당 글이 존재하지 않습니다. boardID: " + boardID));

            BoardLikeEntity boardLikeEntity = BoardLikeEntity.builder()
                    .board(board)
                    .user(user)
                    .build();

            boardLikeRepository.save(boardLikeEntity);
            boardRepository.plusLike(boardID);
            return true;
        }else{
            boardLikeRepository.deleteByBoard_IdAndUser_Id(boardID, user.getId());
            boardRepository.minusLike(boardID);
            return false;
        }
    }

    public Optional<BoardLikeEntity> findLike(Long boardId, Long userID){
        return boardLikeRepository.findByBoard_IdAndUser_Id(boardId, userID);
    }
}
