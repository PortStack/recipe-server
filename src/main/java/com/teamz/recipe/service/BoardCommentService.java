package com.teamz.recipe.service;

import com.teamz.recipe.Dto.BoardCommentDto;
import com.teamz.recipe.Dto.CommentDto;
import com.teamz.recipe.domain.*;
import com.teamz.recipe.repository.AuthRepository;
import com.teamz.recipe.repository.BoardCommentRepository;
import com.teamz.recipe.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardCommentService {

    private final BoardCommentRepository commentRepository;
    private final AuthRepository authRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public Long save(Long boardId, String account, BoardCommentDto.Request dto){
        UserEntity user = authRepository.findByAccount(account).orElseThrow(() ->
                new IllegalArgumentException("댓글 쓰기 실패: 해당 유저가 존재하지 않는다"));

        BoardEntity board = boardRepository.findById(boardId).orElseThrow(() ->
                new IllegalArgumentException("댓글 쓰기 실패: 해당 레시피가 존재하지 않는다"));

        dto.setUser(user);
        dto.setBoard(board);

        BoardCommentEntity commentEntity = dto.toEntity();
        commentRepository.save(commentEntity);

        return commentEntity.getId();
    }

    /* READ */
    @Transactional(readOnly = true)
    public List<BoardCommentDto.Response> findAll(Long id) {
        BoardEntity posts = boardRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id: " + id));

        List<BoardCommentEntity> comments = posts.getComments();

        return comments.stream().map(BoardCommentDto.Response::new).collect(Collectors.toList());
    }

    /* UPDATE */
    @Transactional
    public void update(Long boardId, Long id, BoardCommentDto.Request dto) {
        BoardCommentEntity comment = commentRepository.findByBoard_IdAndId(boardId, id).orElseThrow(() ->
                new IllegalArgumentException("해당 댓글이 존재하지 않습니다. " + id));

        comment.update(dto.getComment());
    }

    /* DELETE */
    @Transactional
    public void delete(Long boardsId, Long id) {
        BoardCommentEntity comment = commentRepository.findByBoard_IdAndId(boardsId, id).orElseThrow(() ->
                new IllegalArgumentException("해당 댓글이 존재하지 않습니다. id=" + id));

        commentRepository.delete(comment);
    }
}
