package com.booktracker.book_tracker.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.booktracker.book_tracker.config.JwtConfig;
import com.booktracker.book_tracker.entities.Token;
import com.booktracker.book_tracker.entities.User;
import com.booktracker.book_tracker.exceptions.ResourceNotFoundException;
import com.booktracker.book_tracker.repositories.TokenRepository;
import com.booktracker.book_tracker.repositories.UserRepository;
import com.booktracker.book_tracker.util.TokenType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenService {
  private final JwtService jwtService;
  private final TokenRepository tokenRepository;
  private final UserRepository userRepository;
  private final JwtConfig jwtConfig;

  public String generateToken(UserDetails userDetails) {
    return jwtService.generateToken(userDetails);
  }

  public String generateRefreshToken(UserDetails userDetails) {
    return jwtService.generateRefreshToken(userDetails);
  }

  public String extractUsername(String token) {
    return jwtService.extractUsername(token);
  }

  public boolean isTokenValid(String token, UserDetails userDetails) {
    return jwtService.isTokenValid(token, userDetails);
  }

  public List<Token> getTokensByUserId(Long userId) {
    if (!userRepository.existsById(userId)) {
      throw new ResourceNotFoundException("Usuario no encontrado");
    }
    return tokenRepository.findAllByUser_Id(userId);
  }

  public void saveAuthTokens(User user, String accessToken, String refreshToken) {
    LocalDateTime now = LocalDateTime.now();

    Token accessTokenEntity = new Token();
    accessTokenEntity.setToken(accessToken);
    accessTokenEntity.setTokenType(TokenType.TOKEN);
    accessTokenEntity.setDateCreated(now);
    accessTokenEntity.setDateExpired(now.plusSeconds(jwtConfig.getTokenExpirationInMillis() / 1000));
    accessTokenEntity.setUser(user);

    Token refreshTokenEntity = new Token();
    refreshTokenEntity.setToken(refreshToken);
    refreshTokenEntity.setTokenType(TokenType.REFRESH_TOKEN);
    refreshTokenEntity.setDateCreated(now);
    refreshTokenEntity.setDateExpired(now.plusSeconds(jwtConfig.getRefreshTokenExpirationInMillis() / 1000));
    refreshTokenEntity.setUser(user);

    tokenRepository.save(accessTokenEntity);
    tokenRepository.save(refreshTokenEntity);
  }
  
}
