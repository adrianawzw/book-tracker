package com.booktracker.book_tracker.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ListaRequestDTO(
        @NotBlank @Size(max = 100) String nombre,
        Long userId
) {}
