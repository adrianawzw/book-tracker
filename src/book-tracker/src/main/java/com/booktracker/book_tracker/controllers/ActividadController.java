package com.booktracker.book_tracker.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booktracker.book_tracker.DTOs.ActividadResponseDTO;
import com.booktracker.book_tracker.services.ActividadService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/actividades")
@RequiredArgsConstructor
public class ActividadController {

    private final ActividadService actividadService;

    @GetMapping
    public List<ActividadResponseDTO> obtenerTodas() {
        return actividadService.obtenerTodas();
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<ActividadResponseDTO> obtenerPorUsuario(
            @PathVariable Long usuarioId) {

        return actividadService.obtenerPorUsuario(usuarioId);
    }
}