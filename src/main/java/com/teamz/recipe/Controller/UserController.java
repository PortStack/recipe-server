package com.teamz.recipe.Controller;

import com.teamz.recipe.Dto.AuthDto;
import com.teamz.recipe.Dto.RefreshTokenDto;
import com.teamz.recipe.Dto.ResponseSingDto;
import com.teamz.recipe.domain.RefreshTokenEntity;
import com.teamz.recipe.domain.UserEntity;
import com.teamz.recipe.global.jwt.JwtProvider;
import com.teamz.recipe.repository.AuthRepository;
import com.teamz.recipe.service.AuthService;
import com.teamz.recipe.service.RefreshTokenService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Slf4j
@Controller
@RequestMapping("/auth")
public class UserController {

    private final AuthService authService;
    private final AuthRepository authRepository;
    private final RefreshTokenService refreshTokenService;
    private final JwtProvider jwtProvider;

    public UserController(AuthService authService, AuthRepository authRepository, RefreshTokenService refreshTokenService, JwtProvider jwtProvider) {
        this.authService = authService;
        this.authRepository = authRepository;
        this.refreshTokenService = refreshTokenService;
        this.jwtProvider = jwtProvider;
    }
    @PostMapping(value = "/login")
    public ResponseEntity<ResponseSingDto> Login(@RequestBody @Valid AuthDto.RequestLogin request, BindingResult bindingResult) throws Exception {

        if (bindingResult.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


        ResponseSingDto responseSingDto = authService.login(request);


        //refreshToken 데이터베이스 저장
        RefreshTokenEntity refreshTokenEntity = RefreshTokenEntity.builder()
                .memberId(responseSingDto.getId())
                .value(responseSingDto.getRefreshToken())
                .build();
        refreshTokenService.addRefreshToken(refreshTokenEntity);

        return new ResponseEntity<>(authService.login(request), HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<Boolean> signup(@RequestBody AuthDto.RequestRegister request, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(authService.register(request), HttpStatus.OK);
    }

//    @GetMapping("/user/get")
//    public ResponseEntity<ResponseSingDto> getUser(@RequestParam String account) throws Exception {
//
//        return new ResponseEntity<>( authService.getUser(account), HttpStatus.OK);
//    }
//
//    @GetMapping("/admin/get")
//    public ResponseEntity<ResponseSingDto> getUserForAdmin(@RequestParam String account) throws Exception {
//        return new ResponseEntity<>( authService.getUser(account), HttpStatus.OK);
//    }

    /*
   1. 전달받은 유저의 아이디로 유저가 존재하는지 확인한다.
   2. RefreshToken이 유효한지 체크한다.
   3. AccessToken을 발급하여 기존 RefreshToken과 함께 응답한다.
    */
    @PostMapping("/refreshToken")
    public ResponseEntity requestRefresh(@RequestBody RefreshTokenDto refreshTokenDto) throws Exception {
        System.out.println(refreshTokenDto.getRefreshToken());
        RefreshTokenEntity refreshTokenEntity = refreshTokenService.findRefreshToken(refreshTokenDto.getRefreshToken()).orElseThrow(() -> new IllegalArgumentException("Refresh token not found"));
        Claims claims = jwtProvider.parseRefreshToken(refreshTokenEntity.getValue());

        Long userId = Long.valueOf((Integer) claims.get("userId"));

        ResponseSingDto responseSingDto = new ResponseSingDto(authService.findByID(userId));

        List role = (List) claims.get("roles");
        String account = claims.getSubject();

        String accessToken = jwtProvider.createAccessToken(account, userId, role);

        responseSingDto.setAccessToken(accessToken);
        responseSingDto.setRefreshToken(refreshTokenDto.getRefreshToken());

        return new ResponseEntity(responseSingDto, HttpStatus.OK);
    }

    @DeleteMapping("/logout")
    public ResponseEntity logout(@RequestBody RefreshTokenDto refreshTokenDto){
        refreshTokenService.deleteRefreshToken(refreshTokenDto);
        System.out.println("test");
        return new ResponseEntity(HttpStatus.OK);
    }


}
