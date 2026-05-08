package com.booktracker.book_tracker.DTOs;

import java.time.LocalDateTime;

public record ActividadResponseDTO(
        Long id,
        Long usuarioId,
        String tipo,
        LocalDateTime fecha
) {}