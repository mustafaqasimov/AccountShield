package com.accountshield.api.repository;

import com.accountshield.api.entity.Token;
import com.accountshield.api.entity.User;
import com.accountshield.api.enums.TokenType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findByTokenValueAndTokenType(String tokenValue, TokenType tokenType);

    //clean old refresh and verification token
    void deleteByUserAndTokenType(User user, TokenType tokenType);
}