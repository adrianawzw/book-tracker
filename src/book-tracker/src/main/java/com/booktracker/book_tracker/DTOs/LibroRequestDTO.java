package com.booktracker.book_tracker.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LibroRequestDTO(
        @NotBlank @Size(max = 200) String titulo,
        @NotBlank @Size(max = 150) String autor,
        @Size(max = 1000) String descripcion,
        String imagenUrl,
        String apiId,
        String fechaPublicacion,
        @Size(max = 100) String genero
) {}