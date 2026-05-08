package com.booktracker.book_tracker.mappers;

import com.booktracker.book_tracker.DTOs.ListaRequestDTO;
import com.booktracker.book_tracker.DTOs.ListaResponseDTO;
import com.booktracker.book_tracker.entities.Lista;
import com.booktracker.book_tracker.entities.User;

public class ListaMapper {

    public static Lista toEntity(ListaRequestDTO dto, User usuario) {
        return Lista.builder()
                .nombre(dto.nombre())
                .usuario(usuario)
                .build();
    }

    public static ListaResponseDTO toDTO(Lista lista) {
        return new ListaResponseDTO(
                lista.getId(),
                lista.getNombre(),
                lista.getUsuario().getId()
        );
    }
}