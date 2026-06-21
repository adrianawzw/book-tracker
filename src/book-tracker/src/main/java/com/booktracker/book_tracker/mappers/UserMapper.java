package com.booktracker.book_tracker.mappers;

import com.booktracker.book_tracker.DTOs.UserRequestDTO;
import com.booktracker.book_tracker.DTOs.UserResponseDTO;
import com.booktracker.book_tracker.entities.User;
import com.booktracker.book_tracker.util.RolesEnum;

public class UserMapper {

    public static User toEntity(UserRequestDTO dto) {
        return User.builder()
                .nombres(dto.nombres())
                .apellidos(dto.apellidos())
                .email(dto.email())
                .password(dto.password())
            .rol(RolesEnum.USER)
                .build();
    }

    public static UserResponseDTO toDTO(User usuario) {
        return new UserResponseDTO(
                usuario.getId(),
                usuario.getNombres(),
                usuario.getApellidos(),
                usuario.getEmail(),
                usuario.getRol() != null ? usuario.getRol().name() : null
        );
    }
}