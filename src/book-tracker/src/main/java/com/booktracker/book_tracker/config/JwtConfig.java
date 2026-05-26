package com.booktracker.book_tracker.config;

import java.nio.charset.StandardCharsets;
import javax.crypto.SecretKey;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.jsonwebtoken.security.Keys;
import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "application.jwt")
public class JwtConfig {
    private String secretKey;
    private Integer tokenExpiration;
    private Integer refreshTokenExpiration;

    public long getTokenExpirationInMillis() {
        return tokenExpiration * 24L * 60 * 60 * 1000;
    }

    public long getRefreshTokenExpirationInMillis() {
        return refreshTokenExpiration * 24L * 60 * 60 * 1000;
    }

    @Bean
    SecretKey secretKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }
}
