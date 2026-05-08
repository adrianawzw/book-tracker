package com.booktracker.book_tracker.DTOs;

import java.time.LocalDateTime;

public record LibroEnListaResponseDTO(
        Long id,
        Long libroId,
        Long listaId,
        LocalDateTime fechaAgregado
) {}