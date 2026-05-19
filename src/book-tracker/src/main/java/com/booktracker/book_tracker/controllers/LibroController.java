package com.booktracker.book_tracker.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.booktracker.book_tracker.DTOs.LibroRequestDTO;
import com.booktracker.book_tracker.DTOs.LibroResponseDTO;
import com.booktracker.book_tracker.services.LibroService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/libros")
@RequiredArgsConstructor
public class LibroController {

    private final LibroService libroService;

    @GetMapping
    public List<LibroResponseDTO> obtenerTodos() {
        return libroService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public LibroResponseDTO obtenerPorId(@PathVariable Long id) {
        return libroService.obtenerPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LibroResponseDTO crearLibro(
            @Valid @RequestBody LibroRequestDTO dto) {

        return libroService.crearLibro(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarLibro(@PathVariable Long id) {
        libroService.eliminarLibro(id);
    }

    @GetMapping("/buscar/titulo")
    public List<LibroResponseDTO> buscarPorTitulo(
            @RequestParam String titulo) {

        return libroService.buscarPorTitulo(titulo);
    }

    @GetMapping("/buscar/autor")
    public List<LibroResponseDTO> buscarPorAutor(
            @RequestParam String autor) {

        return libroService.buscarPorAutor(autor);
    }
}