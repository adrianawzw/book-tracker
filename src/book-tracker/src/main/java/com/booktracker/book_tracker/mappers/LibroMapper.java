package com.booktracker.book_tracker.mappers;

import com.booktracker.book_tracker.DTOs.LibroRequestDTO;
import com.booktracker.book_tracker.DTOs.LibroResponseDTO;
import com.booktracker.book_tracker.entities.Libro;

public class LibroMapper {

    public static Libro toEntity(LibroRequestDTO dto) {
        return Libro.builder()
                .titulo(dto.titulo())
                .autor(dto.autor())
                .descripcion(dto.descripcion())
                .imagenUrl(dto.imagenUrl())
                .apiId(dto.apiId())
                .fechaPublicacion(dto.fechaPublicacion())
                .genero(dto.genero())
                .build();
    }

    public static LibroResponseDTO toDTO(Libro libro) {
        return new LibroResponseDTO(
                libro.getId(),
                libro.getTitulo(),
                libro.getAutor(),
                libro.getDescripcion(),
                libro.getImagenUrl(),
                libro.getApiId(),
                libro.getFechaPublicacion(),
                libro.getGenero()
        );
    }
}