package com.booktracker.book_tracker.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.booktracker.book_tracker.DTOs.UserRequestDTO;
import com.booktracker.book_tracker.DTOs.UserResponseDTO;
import com.booktracker.book_tracker.services.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

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
        @RequestBody UserRequestDTO dto) {

    return userService.actualizarUsuario(id, dto);
}
}