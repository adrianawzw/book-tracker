package com.booktracker.book_tracker.services;

import java.util.List;

import com.booktracker.book_tracker.DTOs.ListaRequestDTO;
import com.booktracker.book_tracker.DTOs.ListaResponseDTO;

public interface ListaService {

    ListaResponseDTO crearLista(ListaRequestDTO dto);

    List<ListaResponseDTO> obtenerTodas();

    ListaResponseDTO obtenerPorId(Long id);

    void eliminarLista(Long id);

    List<ListaResponseDTO> obtenerPorUsuario(Long usuarioId);

    ListaResponseDTO actualizarLista(Long id, ListaRequestDTO dto);
}