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

import com.booktracker.book_tracker.DTOs.LibroRequestDTO;
import com.booktracker.book_tracker.DTOs.LibroResponseDTO;
import com.booktracker.book_tracker.entities.Libro;
import com.booktracker.book_tracker.repositories.LibroRepository;

@ExtendWith(MockitoExtension.class)
public class LibroServiceImplTest {

    @Mock
    private LibroRepository libroRepository;

    @InjectMocks
    private LibroServiceImpl libroService;

    @Test
    void crearLibro_DeberiaGuardarLibro() {

        LibroRequestDTO dto = new LibroRequestDTO(
                "Clean Code",
                "Robert Martin",
                "Libro de buenas prácticas",
                "imagen.jpg",
                "OL123",
                "2008",
                "Programación");

        when(libroRepository.findByApiId("OL123"))
                .thenReturn(Optional.empty());

        Libro libroGuardado = new Libro();
        libroGuardado.setId(1L);
        libroGuardado.setApiId("OL123");

        when(libroRepository.save(any(Libro.class)))
                .thenReturn(libroGuardado);

        LibroResponseDTO response = libroService.crearLibro(dto);

        assertNotNull(response);

        verify(libroRepository).save(any(Libro.class));
    }

    @Test
    void crearLibro_DeberiaLanzarExcepcionSiApiIdExiste() {

        LibroRequestDTO dto = new LibroRequestDTO(
                "Clean Code",
                "Robert Martin",
                "Libro de buenas prácticas",
                "imagen.jpg",
                "OL123",
                "2008",
                "Programación");

        Libro libroExistente = new Libro();

        when(libroRepository.findByApiId("OL123"))
                .thenReturn(Optional.of(libroExistente));

        assertThrows(
                IllegalArgumentException.class,
                () -> libroService.crearLibro(dto));

        verify(libroRepository, never()).save(any());
    }
}
