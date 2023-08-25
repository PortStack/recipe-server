package com.teamz.recipe.service;

import com.teamz.recipe.Dto.RefreshTokenDto;
import com.teamz.recipe.domain.RefreshTokenEntity;
import com.teamz.recipe.repository.AuthRepository;
import com.teamz.recipe.repository.RecipeRepository;
import com.teamz.recipe.repository.RefreshTokenRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public void addRefreshToken(RefreshTokenEntity refreshToken){
        RefreshTokenEntity refreshTokenEntity = refreshTokenRepository.save(refreshToken);
    }

    @Transactional
    public void deleteRefreshToken(RefreshTokenDto refreshTokenDto){
        refreshTokenRepository.findByValue(refreshTokenDto.getRefreshToken()).ifPresent(refreshTokenRepository::delete);
    }

    @Transactional
    public Optional<RefreshTokenEntity> findRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByValue(refreshToken);
    }

}
