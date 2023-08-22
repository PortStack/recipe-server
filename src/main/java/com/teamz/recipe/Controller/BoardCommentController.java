package com.teamz.recipe.Controller;

import com.teamz.recipe.Dto.BoardCommentDto;
import com.teamz.recipe.Dto.CommentDto;
import com.teamz.recipe.service.BoardCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/board/comment")
@RequiredArgsConstructor
@RestController
public class BoardCommentController {

    private final BoardCommentService commentService;

    @PostMapping("/{boardId}/new")
    public ResponseEntity save(@PathVariable Long boardId, @RequestBody BoardCommentDto.Request dto, @AuthenticationPrincipal UserDetails userDetails){
        System.out.println(boardId);
        System.out.println((userDetails.getUsername()));
        System.out.println(dto.getComment());
        return ResponseEntity.ok(commentService.save(boardId,userDetails.getUsername(),dto));
    }

    /* READ */
    @GetMapping("/{boardId}/read")
    public List<BoardCommentDto.Response> read(@PathVariable Long boardId) {
        System.out.println("--------------------------------------------");
        System.out.println(commentService.findAll(boardId));
        return commentService.findAll(boardId);
    }

    /* UPDATE */
    @PutMapping({"/{boardId}/update/{id}"})
    public ResponseEntity<Long> update(@PathVariable Long boardId, @PathVariable Long id, @RequestBody BoardCommentDto.Request dto) {
        System.out.println(boardId);
        System.out.println(dto.getComment());
        commentService.update(boardId, id, dto);
        return ResponseEntity.ok(id);
    }

    /* DELETE */
    @DeleteMapping("/{boardsId}/delete/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long boardsId, @PathVariable Long id) {
        System.out.println("----------------------------------");
        commentService.delete(boardsId, id);
        return ResponseEntity.ok(id);
    }
}
