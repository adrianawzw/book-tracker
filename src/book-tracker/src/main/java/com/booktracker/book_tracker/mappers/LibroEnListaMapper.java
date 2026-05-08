package com.booktracker.book_tracker.mappers;

import java.time.LocalDateTime;

import com.booktracker.book_tracker.DTOs.LibroEnListaRequestDTO;
import com.booktracker.book_tracker.DTOs.LibroEnListaResponseDTO;
import com.booktracker.book_tracker.entities.Libro;
import com.booktracker.book_tracker.entities.LibroEnLista;
import com.booktracker.book_tracker.entities.Lista;

public class LibroEnListaMapper {
        public static LibroEnLista toEntity(LibroEnListaRequestDTO dto, Libro libro, Lista lista) {
        return LibroEnLista.builder()
                .libro(libro)
                .lista(lista)
                .fechaAgregado(LocalDateTime.now())
                .build();
    }

    public static LibroEnListaResponseDTO toDTO(LibroEnLista entity) {
        return new LibroEnListaResponseDTO(
                entity.getId(),
                entity.getLibro().getId(),
                entity.getLista().getId(),
                entity.getFechaAgregado()
        );
    }
}
