package com.booktracker.book_tracker.DTOs;

public record UsuarioResponseDTO(
        Long id,
        String nombres,
        String apellidos,
        String email
) {}