package com.booktracker.book_tracker.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.booktracker.book_tracker.DTOs.LibroEnListaRequestDTO;
import com.booktracker.book_tracker.DTOs.LibroEnListaResponseDTO;
import com.booktracker.book_tracker.services.LibroEnListaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/libros-lista")
@RequiredArgsConstructor
public class LibroEnListaController {

    private final LibroEnListaService libroEnListaService;

    @PostMapping
    public LibroEnListaResponseDTO agregarLibro(
            @Valid @RequestBody LibroEnListaRequestDTO dto) {

        return libroEnListaService.agregarLibroALista(dto);
    }

    @GetMapping
    public List<LibroEnListaResponseDTO> obtenerTodos() {
        return libroEnListaService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public LibroEnListaResponseDTO obtenerPorId(@PathVariable Long id) {
        return libroEnListaService.obtenerPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        libroEnListaService.eliminar(id);
    }

    @GetMapping("/lista/{listaId}")
    public List<LibroEnListaResponseDTO> obtenerPorLista(@PathVariable Long listaId) {
        return libroEnListaService.obtenerPorLista(listaId);
    }

    @GetMapping("/libro/{libroId}")
    public List<LibroEnListaResponseDTO> obtenerPorLibro(@PathVariable Long libroId) {
        return libroEnListaService.obtenerPorLibro(libroId);
    }
}