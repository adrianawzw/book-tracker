package com.booktracker.book_tracker.services.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.booktracker.book_tracker.DTOs.LibroEnListaRequestDTO;
import com.booktracker.book_tracker.exceptions.ResourceNotFoundException;
import com.booktracker.book_tracker.repositories.ActividadRepository;
import com.booktracker.book_tracker.repositories.LibroEnListaRepository;
import com.booktracker.book_tracker.repositories.LibroRepository;

@ExtendWith(MockitoExtension.class)
public class LibroEnListaServiceImplTest {

    @Mock
    private LibroEnListaRepository libroEnListaRepository;

    @Mock
    private ActividadRepository ActividadRepository;

    @Mock
    private LibroRepository libroRepository;

    @InjectMocks
    private LibroEnListaServiceImpl libroEnListaService;


    @Test
    void agregarLibroALista_DeberiaLanzarExcepcionSiLibroNoExiste() {

        LibroEnListaRequestDTO dto = new LibroEnListaRequestDTO(1L, 1L);

        when(libroRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> libroEnListaService.agregarLibroALista(dto));

        verify(libroEnListaRepository, never()).save(any());
    }
}
