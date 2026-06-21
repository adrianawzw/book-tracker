package com.booktracker.book_tracker.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.booktracker.book_tracker.DTOs.ListaRequestDTO;
import com.booktracker.book_tracker.DTOs.ListaResponseDTO;
import com.booktracker.book_tracker.services.ListaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/listas")
@RequiredArgsConstructor
public class ListaController {

    private final ListaService listaService;

    @PostMapping
    public ListaResponseDTO crearLista(
            @Valid @RequestBody ListaRequestDTO dto) {

        return listaService.crearLista(dto);
    }

    @GetMapping
    public List<ListaResponseDTO> obtenerTodas() {
        return listaService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public ListaResponseDTO obtenerPorId(@PathVariable Long id) {
        return listaService.obtenerPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminarLista(@PathVariable Long id) {
        listaService.eliminarLista(id);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<ListaResponseDTO> obtenerPorUsuario(
            @PathVariable Long usuarioId) {

        return listaService.obtenerPorUsuario(usuarioId);
    }
}