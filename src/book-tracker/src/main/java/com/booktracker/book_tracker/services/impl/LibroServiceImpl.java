package com.booktracker.book_tracker.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.booktracker.book_tracker.DTOs.LibroRequestDTO;
import com.booktracker.book_tracker.DTOs.LibroResponseDTO;
import com.booktracker.book_tracker.entities.Libro;
import com.booktracker.book_tracker.mappers.LibroMapper;
import com.booktracker.book_tracker.repositories.LibroRepository;
import com.booktracker.book_tracker.exceptions.ResourceNotFoundException;
import com.booktracker.book_tracker.services.LibroService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LibroServiceImpl implements LibroService {

    private final LibroRepository libroRepository;

    @Override
    public List<LibroResponseDTO> obtenerTodos() {
        return libroRepository.findAll()
                .stream()
                .map(LibroMapper::toDTO)
                .toList();
    }

    @Override
    public LibroResponseDTO obtenerPorId(Long id) {

        Libro libro = libroRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Libro no encontrado"));

        return LibroMapper.toDTO(libro);
    }

    @Override
    public LibroResponseDTO crearLibro(LibroRequestDTO dto) {

        if (dto.apiId() != null &&
                libroRepository.findByApiId(dto.apiId()).isPresent()) {

            throw new IllegalArgumentException("El apiId ya existe");
        }

        Libro libro = LibroMapper.toEntity(dto);

        Libro guardado = libroRepository.save(libro);

        return LibroMapper.toDTO(guardado);
    }

    @Override
    public void eliminarLibro(Long id) {

        if (!libroRepository.existsById(id)) {
            throw new ResourceNotFoundException("Libro no encontrado");
        }

        libroRepository.deleteById(id);
    }

    @Override
    public List<LibroResponseDTO> buscarPorTitulo(String titulo) {
        return libroRepository.findByTituloContainingIgnoreCase(titulo)
                .stream()
                .map(LibroMapper::toDTO)
                .toList();
    }

    @Override
    public List<LibroResponseDTO> buscarPorAutor(String autor) {
        return libroRepository.findByAutorContainingIgnoreCase(autor)
                .stream()
                .map(LibroMapper::toDTO)
                .toList();
    }
}