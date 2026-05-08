package com.booktracker.book_tracker.DTOs;

import java.time.LocalDateTime;

public record ResenaResponseDTO(
        Long id,
        Long usuarioId,
        Long libroId,
        Integer calificacion,
        String comentario,
        LocalDateTime fecha
) {}