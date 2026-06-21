package com.booktracker.book_tracker.services.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.booktracker.book_tracker.DTOs.UserRequestDTO;
import com.booktracker.book_tracker.DTOs.UserResponseDTO;
import com.booktracker.book_tracker.entities.User;
import com.booktracker.book_tracker.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void crearUsuario_DeberiaGuardarUsuario() {

        UserRequestDTO dto = new UserRequestDTO(
                "Juan",
                "Perez",
                "juan@gmail.com",
                "123456");

        when(userRepository.existsByEmail(dto.email()))
                .thenReturn(false);

        when(passwordEncoder.encode(dto.password()))
                .thenReturn("passwordEncriptado");

        User usuarioGuardado = new User();
        usuarioGuardado.setId(1L);

        when(userRepository.save(any(User.class)))
                .thenReturn(usuarioGuardado);

        UserResponseDTO response = userService.crearUsuario(dto);

        assertNotNull(response);

        verify(userRepository).save(any(User.class));
    }

    @Test
    void crearUsuario_DeberiaLanzarExcepcionSiEmailExiste() {

        UserRequestDTO dto = new UserRequestDTO(
                "Juan",
                "Perez",
                "juan@gmail.com",
                "123456");

        when(userRepository.existsByEmail(dto.email()))
                .thenReturn(true);

        assertThrows(
                IllegalArgumentException.class,
                () -> userService.crearUsuario(dto));

        verify(userRepository, never()).save(any());
    }
}
