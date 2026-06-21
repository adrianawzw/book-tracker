package com.booktracker.book_tracker.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.booktracker.book_tracker.DTOs.ActividadResponseDTO;
import com.booktracker.book_tracker.mappers.ActividadMapper;
import com.booktracker.book_tracker.repositories.ActividadRepository;
import com.booktracker.book_tracker.services.ActividadService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActividadServiceImpl implements ActividadService {

    private final ActividadRepository actividadRepository;

    @Override
    public List<ActividadResponseDTO> obtenerTodas() {
        return actividadRepository.findByOrderByFechaDesc()
                .stream()
                .map(ActividadMapper::toDTO)
                .toList();
    }

    @Override
    public List<ActividadResponseDTO> obtenerPorUsuario(Long usuarioId) {
        return actividadRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(ActividadMapper::toDTO)
                .toList();
    }
}