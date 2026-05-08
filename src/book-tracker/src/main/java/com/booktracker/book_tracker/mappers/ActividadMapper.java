package com.booktracker.book_tracker.mappers;

import java.time.LocalDateTime;

import com.booktracker.book_tracker.DTOs.ActividadResponseDTO;
import com.booktracker.book_tracker.entities.Actividad;
import com.booktracker.book_tracker.entities.User;

public class ActividadMapper {
    public static Actividad crearActividad(User usuario, String tipo) {
        return Actividad.builder()
                .usuario(usuario)
                .tipo(tipo)
                .fecha(LocalDateTime.now())
                .build();
    }

    public static ActividadResponseDTO toDTO(Actividad actividad) {
        return new ActividadResponseDTO(
                actividad.getId(),
                actividad.getUsuario().getId(),
                actividad.getTipo(),
                actividad.getFecha()
        );
    }
}
