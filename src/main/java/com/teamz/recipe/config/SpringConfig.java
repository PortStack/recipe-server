package com.teamz.recipe.config;

import com.teamz.recipe.repository.BoardRepository;
import com.teamz.recipe.repository.MemoryBoardRepository;
import com.teamz.recipe.service.BoardService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public BoardRepository boardRepository() {return new MemoryBoardRepository();}

    @Bean
    public BoardService boardService() {return new BoardService(boardRepository());}
}
