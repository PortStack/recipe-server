package com.teamz.recipe;

import com.teamz.recipe.repository.BoardRepository;
import com.teamz.recipe.repository.MemoryBoardRepository;
import com.teamz.recipe.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public BoardRepository boardRepository() {return new MemoryBoardRepository();}

    @Bean
    public BoardService boardService() {return new BoardService(boardRepository());}




}
