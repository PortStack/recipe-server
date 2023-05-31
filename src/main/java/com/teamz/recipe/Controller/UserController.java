package com.teamz.recipe.Controller;

import com.teamz.recipe.Dto.AuthDto;
import com.teamz.recipe.Dto.UserDto;
import com.teamz.recipe.service.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    private final AuthServiceImpl authService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public UserController(AuthServiceImpl authService) {
        this.authService = authService;
    }

    @PostMapping("/auth/register")
    @ResponseBody
    public ResponseEntity<?> register(@RequestBody AuthDto.RequestRegister user){
        UserDto userDto = authService.registerUser(UserDto.builder()
                        .email(user.getEmail())
                        .password(user.getPassword())
                        .nickname(user.getNickname())
                        .build());

        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        System.out.println(user.getNickname());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(AuthDto.ResponseUser.builder()
                        .email(userDto.getEmail())
                        .nickname(userDto.getNickname())
                        .userId(userDto.getUserId())
                        .encryptedPwd(userDto.getEncryptedPwd())
                        .build());
    }

}
