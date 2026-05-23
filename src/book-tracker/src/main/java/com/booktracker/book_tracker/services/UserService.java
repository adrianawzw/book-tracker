package com.booktracker.book_tracker.services;

import java.util.List;

import com.booktracker.book_tracker.DTOs.UserRequestDTO;
import com.booktracker.book_tracker.DTOs.UserResponseDTO;

public interface UserService {

    List<UserResponseDTO> obtenerTodos();

    UserResponseDTO obtenerPorId(Long id);

    UserResponseDTO crearUsuario(UserRequestDTO dto);

    void eliminarUsuario(Long id);

    UserResponseDTO actualizarUsuario(Long id, UserRequestDTO dto);
}