package com.booktracker.book_tracker.DTOs;

public record LibroResponseDTO(
        Long id,
        String titulo,
        String autor,
        String descripcion,
        String imagenUrl,
        String apiId,
        String fechaPublicacion,
        String genero
) {}