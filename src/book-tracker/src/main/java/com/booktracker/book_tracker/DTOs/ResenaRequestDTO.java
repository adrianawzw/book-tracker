package com.booktracker.book_tracker.DTOs;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ResenaRequestDTO(
        Long usuarioId,
        Long libroId,
        @NotNull @Min(1) @Max(5) Integer calificacion,
        @Size(max = 1000) String comentario
) {}
