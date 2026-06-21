package com.booktracker.book_tracker.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.booktracker.book_tracker.DTOs.ResenaRequestDTO;
import com.booktracker.book_tracker.DTOs.ResenaResponseDTO;
import com.booktracker.book_tracker.entities.Libro;
import com.booktracker.book_tracker.entities.Resena;
import com.booktracker.book_tracker.entities.User;
import com.booktracker.book_tracker.mappers.ResenaMapper;
import com.booktracker.book_tracker.repositories.LibroRepository;
import com.booktracker.book_tracker.repositories.ResenaRepository;
import com.booktracker.book_tracker.repositories.UserRepository;
import com.booktracker.book_tracker.exceptions.ResourceNotFoundException;
import com.booktracker.book_tracker.services.ResenaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResenaServiceImpl implements ResenaService {

    private final ResenaRepository resenaRepository;
    private final UserRepository userRepository;
    private final LibroRepository libroRepository;

    @Override
    public ResenaResponseDTO crearResena(ResenaRequestDTO dto) {

        User usuario = userRepository.findById(dto.usuarioId())
            .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        Libro libro = libroRepository.findById(dto.libroId())
            .orElseThrow(() -> new ResourceNotFoundException("Libro no encontrado"));

        Resena existente = resenaRepository
                .findByUsuarioIdAndLibroId(dto.usuarioId(), dto.libroId());

        if (existente != null) {
            throw new IllegalArgumentException("El usuario ya reseñó este libro");
        }

        Resena resena = ResenaMapper.toEntity(dto, usuario, libro);

        return ResenaMapper.toDTO(
                resenaRepository.save(resena)
        );
    }

    @Override
    public List<ResenaResponseDTO> obtenerTodas() {
        return resenaRepository.findAll()
                .stream()
                .map(ResenaMapper::toDTO)
                .toList();
    }

    @Override
    public ResenaResponseDTO obtenerPorId(Long id) {

        Resena resena = resenaRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Reseña no encontrada"));

        return ResenaMapper.toDTO(resena);
    }

    @Override
    public void eliminarResena(Long id) {
        resenaRepository.deleteById(id);
    }

    @Override
    public List<ResenaResponseDTO> obtenerPorUsuario(Long usuarioId) {

        return resenaRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(ResenaMapper::toDTO)
                .toList();
    }

    @Override
    public List<ResenaResponseDTO> obtenerPorLibro(Long libroId) {

        return resenaRepository.findByLibroId(libroId)
                .stream()
                .map(ResenaMapper::toDTO)
                .toList();
    }
}