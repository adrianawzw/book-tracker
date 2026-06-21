package com.booktracker.book_tracker.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.booktracker.book_tracker.DTOs.Request.AuthRequest;
import com.booktracker.book_tracker.DTOs.Request.RefreshTokenRequest;
import com.booktracker.book_tracker.DTOs.Request.RegisterRequest;
import com.booktracker.book_tracker.DTOs.Response.RegisterResponse;
import com.booktracker.book_tracker.DTOs.Response.AuthResponse;
import com.booktracker.book_tracker.entities.User;
import com.booktracker.book_tracker.repositories.UserRepository;
import com.booktracker.book_tracker.util.RolesEnum;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final TokenService tokenService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public RegisterResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            return new RegisterResponse(
                request.email(),
                "Error",
                "El correo electrónico ya está registrado"
            );
        }

        var user = new User();
        user.setNombres(request.nombres());
        user.setApellidos(request.apellidos());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.clave()));
        user.setRol(RolesEnum.USER);

        userRepository.save(user);
        return new RegisterResponse(
            request.email(),
            "success",
            "Usuario registrado exitosamente"
        );
    }

    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password()));

        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + request.email()));

        String accessToken = tokenService.generateToken(user);
        String refreshToken = tokenService.generateRefreshToken(user);
        tokenService.saveAuthTokens(user, accessToken, refreshToken);

        return new AuthResponse(accessToken, refreshToken);
    }

    public AuthResponse refreshToken(RefreshTokenRequest request) {
        String userEmail = tokenService.extractUsername(request.refreshToken());
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + userEmail));

        if (!tokenService.isTokenValid(request.refreshToken(), user)) {
            throw new RuntimeException("Token inválido o expirado");
        }

        String newAccessToken = tokenService.generateToken(user);
        String newRefreshToken = tokenService.generateRefreshToken(user);
        tokenService.saveAuthTokens(user, newAccessToken, newRefreshToken);

        return new AuthResponse(newAccessToken, newRefreshToken);
    }

}
