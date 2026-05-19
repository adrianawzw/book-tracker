package com.booktracker.book_tracker.services;

import java.util.List;

import com.booktracker.book_tracker.DTOs.LibroRequestDTO;
import com.booktracker.book_tracker.DTOs.LibroResponseDTO;

public interface LibroService {

    List<LibroResponseDTO> obtenerTodos();

    LibroResponseDTO obtenerPorId(Long id);

    LibroResponseDTO crearLibro(LibroRequestDTO dto);

    void eliminarLibro(Long id);

    List<LibroResponseDTO> buscarPorTitulo(String titulo);

    List<LibroResponseDTO> buscarPorAutor(String autor);
}