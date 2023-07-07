package com.teamz.recipe.repository;

import com.teamz.recipe.domain.BoardCommentEntity;
import com.teamz.recipe.domain.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardCommentRepository extends JpaRepository<BoardCommentEntity, String> {

    List<BoardCommentEntity> getCommentByBoardOrderById(BoardEntity board);

    Optional<BoardCommentEntity> findByBoard_IdAndId(Long board_id, Long id);
}
