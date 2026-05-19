package com.booktracker.book_tracker.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.booktracker.book_tracker.DTOs.ResenaRequestDTO;
import com.booktracker.book_tracker.DTOs.ResenaResponseDTO;
import com.booktracker.book_tracker.services.ResenaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/resenas")
@RequiredArgsConstructor
public class ResenaController {

    private final ResenaService resenaService;

    @PostMapping
    public ResenaResponseDTO crearResena(
            @Valid @RequestBody ResenaRequestDTO dto) {

        return resenaService.crearResena(dto);
    }

    @GetMapping
    public List<ResenaResponseDTO> obtenerTodas() {
        return resenaService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public ResenaResponseDTO obtenerPorId(@PathVariable Long id) {
        return resenaService.obtenerPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminarResena(@PathVariable Long id) {
        resenaService.eliminarResena(id);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<ResenaResponseDTO> obtenerPorUsuario(
            @PathVariable Long usuarioId) {

        return resenaService.obtenerPorUsuario(usuarioId);
    }

    @GetMapping("/libro/{libroId}")
    public List<ResenaResponseDTO> obtenerPorLibro(
            @PathVariable Long libroId) {

        return resenaService.obtenerPorLibro(libroId);
    }
}