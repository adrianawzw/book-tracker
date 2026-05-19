package com.booktracker.book_tracker.services;

import java.util.List;

import com.booktracker.book_tracker.DTOs.ActividadResponseDTO;

public interface ActividadService {

    List<ActividadResponseDTO> obtenerTodas();

    List<ActividadResponseDTO> obtenerPorUsuario(Long usuarioId);
}