package com.booktracker.book_tracker.services.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.booktracker.book_tracker.DTOs.LibroEnListaRequestDTO;
import com.booktracker.book_tracker.DTOs.LibroEnListaResponseDTO;
import com.booktracker.book_tracker.entities.Actividad;
import com.booktracker.book_tracker.entities.Libro;
import com.booktracker.book_tracker.entities.LibroEnLista;
import com.booktracker.book_tracker.entities.Lista;
import com.booktracker.book_tracker.mappers.LibroEnListaMapper;
import com.booktracker.book_tracker.repositories.ActividadRepository;
import com.booktracker.book_tracker.repositories.LibroEnListaRepository;
import com.booktracker.book_tracker.repositories.LibroRepository;
import com.booktracker.book_tracker.repositories.ListaRepository;
import com.booktracker.book_tracker.exceptions.ResourceNotFoundException;
import com.booktracker.book_tracker.services.LibroEnListaService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LibroEnListaServiceImpl implements LibroEnListaService {

    private final LibroEnListaRepository libroEnListaRepository;
    private final LibroRepository libroRepository;
    private final ListaRepository listaRepository;
    private final ActividadRepository actividadRepository;

    @Override
    @Transactional
    public LibroEnListaResponseDTO agregarLibroALista(LibroEnListaRequestDTO dto) {

        Libro libro = libroRepository.findById(dto.libroId())
            .orElseThrow(() -> new ResourceNotFoundException("Libro no encontrado"));

        Lista lista = listaRepository.findById(dto.listaId())
            .orElseThrow(() -> new ResourceNotFoundException("Lista no encontrada"));

        LibroEnLista entity = LibroEnListaMapper.toEntity(dto, libro, lista);

        LibroEnLista libroEnListaGuardada = libroEnListaRepository.save(entity);

        Actividad actividad = Actividad.builder()
                .usuario(lista.getUsuario())
                .tipo("AGREGO_LIBRO")
                .fecha(LocalDateTime.now())
                .build();

        actividadRepository.save(actividad);

        return LibroEnListaMapper.toDTO(libroEnListaGuardada);
    }

    @Override
    public List<LibroEnListaResponseDTO> obtenerTodos() {
        return libroEnListaRepository.findAll()
                .stream()
                .map(LibroEnListaMapper::toDTO)
                .toList();
    }

    @Override
    public LibroEnListaResponseDTO obtenerPorId(Long id) {
        LibroEnLista entity = libroEnListaRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Registro no encontrado"));

        return LibroEnListaMapper.toDTO(entity);
    }

    @Override
    public void eliminar(Long id) {
        libroEnListaRepository.deleteById(id);
    }

    @Override
    public List<LibroEnListaResponseDTO> obtenerPorLista(Long listaId) {
        return libroEnListaRepository.findByListaId(listaId)
                .stream()
                .map(LibroEnListaMapper::toDTO)
                .toList();
    }

    @Override
    public List<LibroEnListaResponseDTO> obtenerPorLibro(Long libroId) {
        return libroEnListaRepository.findByLibroId(libroId)
                .stream()
                .map(LibroEnListaMapper::toDTO)
                .toList();
    }
}