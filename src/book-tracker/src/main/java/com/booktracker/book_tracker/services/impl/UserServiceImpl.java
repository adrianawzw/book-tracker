package com.booktracker.book_tracker.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.booktracker.book_tracker.DTOs.UserRequestDTO;
import com.booktracker.book_tracker.DTOs.UserResponseDTO;
import com.booktracker.book_tracker.entities.User;
import com.booktracker.book_tracker.mappers.UserMapper;
import com.booktracker.book_tracker.repositories.UserRepository;
import com.booktracker.book_tracker.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserResponseDTO> obtenerTodos() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDTO)
                .toList();
    }

    @Override
    public UserResponseDTO obtenerPorId(Long id) {

        User usuario = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return UserMapper.toDTO(usuario);
    }

    @Override
    public UserResponseDTO crearUsuario(UserRequestDTO dto) {

        if (userRepository.existsByEmail(dto.email())) {
            throw new RuntimeException("El email ya está registrado");
        }

        User usuario = UserMapper.toEntity(dto);

        User guardado = userRepository.save(usuario);

        return UserMapper.toDTO(guardado);
    }

    @Override
    public void eliminarUsuario(Long id) {

        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado");
        }

        userRepository.deleteById(id);
    }
}