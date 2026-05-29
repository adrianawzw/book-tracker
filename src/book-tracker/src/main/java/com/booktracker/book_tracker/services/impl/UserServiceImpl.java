package com.booktracker.book_tracker.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.booktracker.book_tracker.DTOs.UserRequestDTO;
import com.booktracker.book_tracker.DTOs.UserResponseDTO;
import com.booktracker.book_tracker.entities.User;
import com.booktracker.book_tracker.mappers.UserMapper;
import com.booktracker.book_tracker.repositories.UserRepository;
import com.booktracker.book_tracker.exceptions.ResourceNotFoundException;
import com.booktracker.book_tracker.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {



    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;



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
<<<<<<< HEAD
            .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
=======

                .orElseThrow(() -> new IllegalStateException("Usuario inválido"));
>>>>>>> develop

        return UserMapper.toDTO(usuario);
    }

    @Override
    public UserResponseDTO crearUsuario(UserRequestDTO dto) {

        if (userRepository.existsByEmail(dto.email())) {
            throw new IllegalArgumentException("El email ya está registrado");
        }

        User usuario = UserMapper.toEntity(dto);
        usuario.setPassword(passwordEncoder.encode(dto.password()));

        User guardado = userRepository.save(usuario);

        return UserMapper.toDTO(guardado);
    }

    @Override
    public void eliminarUsuario(Long id) {

        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuario no encontrado");
        }

        userRepository.deleteById(id);
    }

    @Override
    public UserResponseDTO actualizarUsuario(Long id, UserRequestDTO dto) {

            User usuario = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        usuario.setNombres(dto.nombres());
        usuario.setApellidos(dto.apellidos());
        usuario.setEmail(dto.email());
        usuario.setPassword(passwordEncoder.encode(dto.password()));

        User actualizado = userRepository.save(usuario);

        return UserMapper.toDTO(actualizado);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + username));
    }
}