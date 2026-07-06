package com.booktracker.book_tracker.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.booktracker.book_tracker.DTOs.UserRequestDTO;
import com.booktracker.book_tracker.DTOs.UserResponseDTO;
import com.booktracker.book_tracker.entities.Token;
import com.booktracker.book_tracker.services.TokenService;
import com.booktracker.book_tracker.services.UserService;

import org.springframework.security.core.Authentication;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final TokenService tokenService;

    @GetMapping
    public List<UserResponseDTO> obtenerTodos() {
        return userService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public UserResponseDTO obtenerPorId(@PathVariable Long id) {
        return userService.obtenerPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO crearUsuario(
            @Valid @RequestBody UserRequestDTO dto) {

        return userService.crearUsuario(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarUsuario(@PathVariable Long id) {
        userService.eliminarUsuario(id);
    }

    @PutMapping("/{id}")
    public UserResponseDTO actualizarUsuario(
            @PathVariable Long id,
            @Valid @RequestBody UserRequestDTO dto) {

        return userService.actualizarUsuario(id, dto);
    }

    @GetMapping("/me")
    public UserResponseDTO obtenerUsuarioActual(Authentication authentication) {
        String email = authentication.getName();
        return userService.obtenerPorEmail(email);
    }

    @GetMapping("/user")
    public String userMethod() {
        return "Looged as user or admin";
    }

    @GetMapping("/admin")
    public String adminMethod() {
        return "Looged as admin";
    }

    @GetMapping("/admin/get-tokens/{id}")
    public List<Token> adminMethodGetTokensByUser(@PathVariable Long id) {
        return tokenService.getTokensByUserId(id);
    }
}