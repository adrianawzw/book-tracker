package com.booktracker.book_tracker.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.booktracker.book_tracker.DTOs.ActividadResponseDTO;
import com.booktracker.book_tracker.entities.Actividad;
import com.booktracker.book_tracker.entities.User;
import com.booktracker.book_tracker.repositories.ActividadRepository;

@ExtendWith(MockitoExtension.class)
public class ActividadServiceImplTest {

    @Mock
    private ActividadRepository actividadRepository;

    @InjectMocks
    private ActividadServiceImpl actividadService;

    @Test
    void obtenerTodas_DeberiaRetornarListaOrdenada() {

        User usuario = new User();
        usuario.setId(1L);

        Actividad actividad = Actividad.builder()
                .id(1L)
                .usuario(usuario)
                .tipo("CREO_LISTA")
                .fecha(LocalDateTime.now())
                .build();

        when(actividadRepository.findByOrderByFechaDesc())
                .thenReturn(List.of(actividad));

        List<ActividadResponseDTO> resultado = actividadService.obtenerTodas();

        assertEquals(1, resultado.size());

        verify(actividadRepository).findByOrderByFechaDesc();
    }

    @Test
    void obtenerPorUsuario_DeberiaRetornarActividades() {

        User usuario = new User();
        usuario.setId(1L);

        Actividad actividad = Actividad.builder()
                .id(1L)
                .usuario(usuario)
                .tipo("CALIFICO_LIBRO")
                .fecha(LocalDateTime.now())
                .build();

        when(actividadRepository.findByUsuarioId(1L))
                .thenReturn(List.of(actividad));

        List<ActividadResponseDTO> resultado = actividadService.obtenerPorUsuario(1L);

        assertEquals(1, resultado.size());

        verify(actividadRepository).findByUsuarioId(1L);
    }
}
