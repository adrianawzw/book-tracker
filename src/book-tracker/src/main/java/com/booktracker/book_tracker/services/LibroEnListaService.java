package com.booktracker.book_tracker.services;

import java.util.List;

import com.booktracker.book_tracker.DTOs.LibroEnListaRequestDTO;
import com.booktracker.book_tracker.DTOs.LibroEnListaResponseDTO;

public interface LibroEnListaService {

    LibroEnListaResponseDTO agregarLibroALista(LibroEnListaRequestDTO dto);

    List<LibroEnListaResponseDTO> obtenerTodos();

    LibroEnListaResponseDTO obtenerPorId(Long id);

    void eliminar(Long id);

    List<LibroEnListaResponseDTO> obtenerPorLista(Long listaId);

    List<LibroEnListaResponseDTO> obtenerPorLibro(Long libroId);
}