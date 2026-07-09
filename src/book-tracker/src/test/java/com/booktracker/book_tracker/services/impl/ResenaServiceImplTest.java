package com.booktracker.book_tracker.services.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.booktracker.book_tracker.DTOs.ResenaRequestDTO;
import com.booktracker.book_tracker.DTOs.ResenaResponseDTO;
import com.booktracker.book_tracker.entities.Libro;
import com.booktracker.book_tracker.entities.Resena;
import com.booktracker.book_tracker.entities.User;
import com.booktracker.book_tracker.exceptions.ResourceNotFoundException;
import com.booktracker.book_tracker.repositories.LibroRepository;
import com.booktracker.book_tracker.repositories.ResenaRepository;
import com.booktracker.book_tracker.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
class ResenaServiceImplTest {

    @Mock
    private ResenaRepository resenaRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private LibroRepository libroRepository;

    @InjectMocks
    private ResenaServiceImpl resenaService;

    @Test
    void crearResena_DeberiaGuardarResena() {

        ResenaRequestDTO dto = new ResenaRequestDTO(
                1L,
                1L,
                5,
                "Excelente libro");

        User usuario = new User();
        usuario.setId(1L);

        Libro libro = new Libro();
        libro.setId(1L);

        Resena resena = new Resena();
        resena.setUsuario(usuario);
        resena.setLibro(libro);
        resena.setComentario("Excelente libro");
        resena.setCalificacion(5);

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(usuario));

        when(libroRepository.findById(1L))
                .thenReturn(Optional.of(libro));

        when(resenaRepository.findByUsuarioIdAndLibroId(1L, 1L))
                .thenReturn(null);

        when(resenaRepository.save(any(Resena.class)))
                .thenReturn(resena);

        ResenaResponseDTO respuesta = resenaService.crearResena(dto);

        assertNotNull(respuesta);

        verify(resenaRepository).save(any(Resena.class));
    }

    @Test
    void crearResena_DeberiaLanzarExcepcionSiUsuarioNoExiste() {

        ResenaRequestDTO dto = new ResenaRequestDTO(
                1L,
                1L,
                4,
                "Comentario");

        when(userRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> resenaService.crearResena(dto));

        verify(resenaRepository, never()).save(any());
    }

    @Test
    void crearResena_DeberiaLanzarExcepcionSiLibroNoExiste() {

        ResenaRequestDTO dto = new ResenaRequestDTO(
                1L,
                1L,
                4,
                "Comentario");

        User usuario = new User();
        usuario.setId(1L);

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(usuario));

        when(libroRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> resenaService.crearResena(dto));

        verify(resenaRepository, never()).save(any());
    }

    @Test
    void crearResena_DeberiaLanzarExcepcionSiYaExisteResena() {

        ResenaRequestDTO dto = new ResenaRequestDTO(
                1L,
                1L,
                5,
                "Comentario");

        User usuario = new User();
        usuario.setId(1L);

        Libro libro = new Libro();
        libro.setId(1L);

        Resena existente = new Resena();

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(usuario));

        when(libroRepository.findById(1L))
                .thenReturn(Optional.of(libro));

        when(resenaRepository.findByUsuarioIdAndLibroId(1L, 1L))
                .thenReturn(existente);

        assertThrows(
                IllegalArgumentException.class,
                () -> resenaService.crearResena(dto));

        verify(resenaRepository, never()).save(any());
    }

}