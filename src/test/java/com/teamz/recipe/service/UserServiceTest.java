package com.teamz.recipe.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Test
    void 모든_규칙을_충족하는_경우(){
        String id = "test";
        String password = "@password12123";

        boolean passwordResult = userService.ConfirmationPassword(password);
        Assertions.assertThat(true).isEqualTo(passwordResult);
    }

    @Test
    void 비밀번호를_입력하지_않은_경우(){
        String id = "test";
        String password = "";

        boolean passwordResult = userService.ConfirmationPassword(password);
        Assertions.assertThat(false).isEqualTo(passwordResult);
    }

    @Test
    void 글자수_부족(){
        String id = "test";
        String password = "aaa123!";

        boolean passwordResult = userService.ConfirmationPassword(password);
        Assertions.assertThat(false).isEqualTo(passwordResult);
    }

    @Test
    void 특수문자_없음(){
        String id = "test";
        String password = "aaa12303";

        boolean passwordResult = userService.ConfirmationPassword(password);
        Assertions.assertThat(false).isEqualTo(passwordResult);
    }

    @Test
    void 영어_없음(){
        String id = "test";
        String password = "152354123!";

        boolean passwordResult = userService.ConfirmationPassword(password);
        Assertions.assertThat(false).isEqualTo(passwordResult);
    }

    @Test
    void 숫자없음(){
        String id = "test";
        String password = "asd!#%asd!";

        boolean passwordResult = userService.ConfirmationPassword(password);
        Assertions.assertThat(false).isEqualTo(passwordResult);
    }

    @Autowired  AuthService userService;
}