package com.teamz.recipe.Controller;

import com.teamz.recipe.Dto.BoardDto;
import com.teamz.recipe.Dto.RecipeDto;
import com.teamz.recipe.domain.*;
import com.teamz.recipe.repository.AuthRepository;
import com.teamz.recipe.service.AuthService;
import com.teamz.recipe.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/board")
@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;
    private final AuthRepository authRepository;

    @PostMapping("/new")
    public ResponseEntity save(BoardDto.Request dto, @AuthenticationPrincipal UserDetails userDetails) throws Exception {
        return ResponseEntity.ok(boardService.save(dto));
    }

    @GetMapping("/read/{id}")
    public ResponseEntity read(@PathVariable Long id,@RequestParam(value = "nickname" , required = false, defaultValue="noLogin") String nickname) throws Exception {
        boolean likeState = false;

        boardService.updateView(id);
        BoardEntity boardEntity = boardService.findById(id);

        if(!nickname.equals("noLogin")){
            UserEntity userEntity = authRepository.findByAccount(nickname)
                    .orElseThrow(() -> new Exception("계정을 찾을 수 없습니다. " + nickname));
        }
        BoardDto.Response responseDto = new BoardDto.Response(boardEntity);

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity retrieveBoard(@PageableDefault(size=10, sort="id", direction = Sort.Direction.DESC) Pageable pageable){
        Page<BoardEntity> posts = boardService.pageList(pageable);
        return ResponseEntity.ok(posts.map(m -> new BoardDto.Response(m)));
    }

    @GetMapping("/like/{idx}")
    public ResponseEntity like(@PathVariable Long idx,@AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(boardService.saveLike(idx,userDetails.getUsername()));
    }
}
