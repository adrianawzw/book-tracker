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

import com.booktracker.book_tracker.DTOs.ListaRequestDTO;
import com.booktracker.book_tracker.DTOs.ListaResponseDTO;
import com.booktracker.book_tracker.entities.Actividad;
import com.booktracker.book_tracker.entities.Lista;
import com.booktracker.book_tracker.entities.User;
import com.booktracker.book_tracker.exceptions.ResourceNotFoundException;
import com.booktracker.book_tracker.repositories.ActividadRepository;
import com.booktracker.book_tracker.repositories.ListaRepository;
import com.booktracker.book_tracker.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
class ListaServiceImplTest {

    @Mock
    private ListaRepository listaRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ActividadRepository actividadRepository;

    @InjectMocks
    private ListaServiceImpl listaService;

    @Test
    void crearLista_DeberiaGuardarListaYActividad() {

        User usuario = new User();
        usuario.setId(1L);

        Lista listaGuardada = new Lista();
        listaGuardada.setId(10L);
        listaGuardada.setUsuario(usuario);
        listaGuardada.setNombre("Favoritos");

        ListaRequestDTO dto = new ListaRequestDTO(
                "Favoritos",
                1L);

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(usuario));
                

        when(listaRepository.save(any(Lista.class)))
                .thenReturn(listaGuardada);

        ListaResponseDTO response = listaService.crearLista(dto);

        assertNotNull(response);

        verify(listaRepository).save(any(Lista.class));
        verify(actividadRepository).save(any(Actividad.class));
    }

    @Test
    void crearLista_DeberiaLanzarExcepcionSiUsuarioNoExiste() {

        ListaRequestDTO dto = new ListaRequestDTO(
                "Favoritos",
                999L);

        when(userRepository.findById(999L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> listaService.crearLista(dto));

        verify(listaRepository, never()).save(any());
    }
}
