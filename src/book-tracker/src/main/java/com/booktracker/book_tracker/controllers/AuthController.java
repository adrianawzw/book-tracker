package com.booktracker.book_tracker.controllers;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booktracker.book_tracker.DTOs.Request.AuthRequest;
import com.booktracker.book_tracker.DTOs.Request.RefreshTokenRequest;
import com.booktracker.book_tracker.DTOs.Request.RegisterRequest;
import com.booktracker.book_tracker.DTOs.Response.AuthResponse;
import com.booktracker.book_tracker.DTOs.Response.RegisterResponse;
import com.booktracker.book_tracker.services.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/registrar")
  public ResponseEntity<RegisterResponse> register(
      @RequestBody RegisterRequest request) {
    return ResponseEntity.ok(authService.register(request));
  }

  @PostMapping("/autenticar")
  public ResponseEntity<AuthResponse> authenticate(
      @RequestBody AuthRequest request) {
    return ResponseEntity.ok(authService.authenticate(request));
  }

  @PostMapping("/refresh-token")
  public ResponseEntity<?> refreshToken(
      @RequestBody RefreshTokenRequest request) {
    try {
      return ResponseEntity.ok(authService.refreshToken(request));
    } catch (RuntimeException ex) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
          .body(Map.of("Error", ex.getMessage()));
    }
  }
}
