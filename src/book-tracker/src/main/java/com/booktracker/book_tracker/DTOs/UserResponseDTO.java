package com.booktracker.book_tracker.DTOs;

public record UserResponseDTO(
        Long id,
        String nombres,
        String apellidos,
        String email,
        String rol
) {}