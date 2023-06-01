package com.teamz.recipe.Controller;

import com.teamz.recipe.Dto.AuthDto;
import com.teamz.recipe.Dto.ResponseSingDto;
import com.teamz.recipe.Dto.UserDto;
import com.teamz.recipe.global.Dto.BaseResponse;
import com.teamz.recipe.global.Dto.SingleDataResponse;
import com.teamz.recipe.global.Service.ResponseService;
import com.teamz.recipe.global.exception.LoginFailedException;
import com.teamz.recipe.repository.AuthRepository;
import com.teamz.recipe.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Controller
@RequestMapping("/auth")
public class UserController {

    private final AuthService authService;
    private final AuthRepository authRepository;

    public UserController(AuthService authService, AuthRepository authRepository) {
        this.authService = authService;
        this.authRepository = authRepository;
    }
    @PostMapping(value = "/login")
    public ResponseEntity<ResponseSingDto> Login(@RequestBody AuthDto.RequestLogin request) throws Exception {
        return new ResponseEntity<>(authService.login(request), HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<Boolean> signup(@RequestBody AuthDto.RequestRegister request) throws Exception {
        return new ResponseEntity<>(authService.register(request), HttpStatus.OK);
    }

    @GetMapping("/user/get")
    public ResponseEntity<ResponseSingDto> getUser(@RequestParam String account) throws Exception {

        return new ResponseEntity<>( authService.getUser(account), HttpStatus.OK);
    }

    @GetMapping("/admin/get")
    public ResponseEntity<ResponseSingDto> getUserForAdmin(@RequestParam String account) throws Exception {
        return new ResponseEntity<>( authService.getUser(account), HttpStatus.OK);
    }


}
