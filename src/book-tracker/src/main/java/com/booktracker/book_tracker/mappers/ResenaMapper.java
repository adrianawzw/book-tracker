package com.booktracker.book_tracker.mappers;

import java.time.LocalDateTime;

import com.booktracker.book_tracker.DTOs.ResenaRequestDTO;
import com.booktracker.book_tracker.DTOs.ResenaResponseDTO;
import com.booktracker.book_tracker.entities.Libro;
import com.booktracker.book_tracker.entities.Resena;
import com.booktracker.book_tracker.entities.User;

public class ResenaMapper {
        public static Resena toEntity(ResenaRequestDTO dto, User usuario, Libro libro) {
        return Resena.builder()
                .usuario(usuario)
                .libro(libro)
                .calificacion(dto.calificacion())
                .comentario(dto.comentario())
                .fecha(LocalDateTime.now())
                .build();
    }

    public static ResenaResponseDTO toDTO(Resena resena) {
        return new ResenaResponseDTO(
                resena.getId(),
                resena.getUsuario().getId(),
                resena.getLibro().getId(),
                resena.getCalificacion(),
                resena.getComentario(),
                resena.getFecha()
        );
    }
}
