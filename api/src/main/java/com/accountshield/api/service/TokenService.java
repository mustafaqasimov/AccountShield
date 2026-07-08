package com.accountshield.api.service;

import com.accountshield.api.dto.LoginResponse;
import com.accountshield.api.entity.Token;
import com.accountshield.api.entity.User;
import com.accountshield.api.enums.ActiveStatus;
import com.accountshield.api.enums.TokenType;
import com.accountshield.api.repository.TokenRepository;
import com.accountshield.api.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final JWTService jwtService;

    @Transactional
    public Token createToken(User user, TokenType type, Long expiryMs) {
        tokenRepository.deleteByUserAndTokenType(user, type);

        Token token = Token.builder()
                .user(user)
                .tokenValue(UUID.randomUUID().toString())
                .tokenType(type)
                .expiryDate(Instant.now().plusMillis(expiryMs))
                .build();

        return tokenRepository.save(token);
    }

    @Transactional
    public LoginResponse refreshAccessToken(String refreshTokenValue) {
        Token token = tokenRepository.findByTokenValueAndTokenType(refreshTokenValue, TokenType.REFRESH)
                .orElseThrow(() -> new RuntimeException("Refresh token not found!"));

        if (token.getExpiryDate().isBefore(Instant.now())) {
            tokenRepository.delete(token);
            throw new RuntimeException("Refresh token's expiry date has passed, please login again!");
        }

        User user = token.getUser();
        tokenRepository.delete(token);

        String newAccessToken = jwtService.generateToken(user);
        Token newRefreshToken = createToken(user, TokenType.REFRESH, 604800000L);

        return LoginResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken.getTokenValue())
                .username(user.getUsername())
                .build();
    }

    @Transactional
    public String verifyEmail(String tokenValue) {
        Token token = tokenRepository.findByTokenValueAndTokenType(tokenValue, TokenType.VERIFICATION)
                .orElseThrow(() -> new RuntimeException("Invalid verification token!"));

        if (token.getExpiryDate().isBefore(Instant.now())) {
            tokenRepository.delete(token);
            throw new RuntimeException("Token's expiry date has passed!");
        }

        User user = token.getUser();
        user.setActiveStatus(ActiveStatus.ACTIVE);
        userRepository.save(user);

        tokenRepository.delete(token);
        return "Account verified successfully!";
    }
}

