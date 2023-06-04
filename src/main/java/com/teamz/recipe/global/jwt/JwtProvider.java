package com.teamz.recipe.global.jwt;

import com.teamz.recipe.domain.Authority;
import com.teamz.recipe.global.Service.JpaUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Component
public class JwtProvider {

    @Value("${jwt.secret.accessKey}")
    private String accessToken;

    @Value("${jwt.secret.refreshKey}")
    private String refreshToken;

    private Key accessKey;
    private Key refreshKey;

    // 만료시간 : 1Hour
    private final long accessTokenValidTime = 1000L * 60 * 60;

    //refreshToken 만료기간 : 14 Day
    private final long refreshTokenValidTime = 1000L * 60 * 60 * 24 * 15;

    Date now = new Date();

    private final JpaUserDetailsService userDetailsService;

    @PostConstruct
    protected void init() {
        accessKey = Keys.hmacShaKeyFor(accessToken.getBytes(StandardCharsets.UTF_8));
        refreshKey = Keys.hmacShaKeyFor(refreshToken.getBytes(StandardCharsets.UTF_8));
    }

    // access토큰 생성
    public String createAccessToken(String account,Long userId, List<Authority> roles) {
        Claims claims = Jwts.claims().setSubject(account);
        claims.put("userId", userId);
        claims.put("roles", roles);


        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + accessTokenValidTime))
                .signWith(accessKey, SignatureAlgorithm.HS256)
                .compact();
    }

    /*
    refresh Token 생성
     */
    public String createRefreshToken(String account, Long userId, List<Authority> roles) {
        Claims claims = Jwts.claims().setSubject(account);
        claims.put("userId", userId);
        claims.put("roles", roles);

        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + refreshTokenValidTime)) // set Expire Time
                .signWith(refreshKey, SignatureAlgorithm.HS256)  // 사용할 암호화 알고리즘과
                // signature 에 들어갈 secret값 세팅
                .compact();
    }

    // 권한정보 획득
    // Spring Security 인증과정에서 권한확인을 위한 기능
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getAccount(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // 토큰에 담겨있는 유저 account 획득
    public String getAccount(String token) {
        return Jwts.parserBuilder().setSigningKey(accessKey).build().parseClaimsJws(token).getBody().getSubject();
    }

    // Authorization Header를 통해 인증을 한다.
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    // 토큰 검증
    public boolean validateToken(String token) {
        try {
            // Bearer 검증
            if (!token.substring(0, "BEARER ".length()).equalsIgnoreCase("BEARER ")) {
                return false;
            } else {
                token = token.split(" ")[1].trim();
            }
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(accessKey).build().parseClaimsJws(token);
            // 만료되었을 시 false
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public Claims parseAccessToken(String accessToken) {
        return parseToken(accessToken, accessKey);
    }

    public Claims parseRefreshToken(String refreshToken) {
        return parseToken(refreshToken, refreshKey);
    }

    public Claims parseToken(String token, Key secretKey) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
