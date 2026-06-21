package com.booktracker.book_tracker.services;

import java.util.List;

import com.booktracker.book_tracker.DTOs.ResenaRequestDTO;
import com.booktracker.book_tracker.DTOs.ResenaResponseDTO;

public interface ResenaService {

    ResenaResponseDTO crearResena(ResenaRequestDTO dto);

    List<ResenaResponseDTO> obtenerTodas();

    ResenaResponseDTO obtenerPorId(Long id);

    void eliminarResena(Long id);

    List<ResenaResponseDTO> obtenerPorUsuario(Long usuarioId);

    List<ResenaResponseDTO> obtenerPorLibro(Long libroId);
}