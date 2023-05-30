//package com.teamz.recipe.repository;
//
//import com.teamz.recipe.domain.User;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class MemoryUserRepositoryTest {
//
//    MemoryUserRepository repository = new MemoryUserRepository();
//
//    @AfterEach
//    public void afterEach(){
//        repository.clearStore();
//    }
//
//    @Test
//    public void save() {
//        //given
//        User user = new User();
//        user.setId(1L);
//        user.setNickname("test2");
//        user.setPassword("!aae12345");
//        user.setEmail("aaa123@gmail.com");
//
//        //when
//        repository.register(user);
//
//        //then
//        User result = repository.findById(user.getId()).get();
//        assertEquals(user,result);
//
//    }
//
//}