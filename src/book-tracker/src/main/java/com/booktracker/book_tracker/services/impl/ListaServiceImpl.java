package com.booktracker.book_tracker.services.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.booktracker.book_tracker.DTOs.ListaRequestDTO;
import com.booktracker.book_tracker.DTOs.ListaResponseDTO;
import com.booktracker.book_tracker.entities.Actividad;
import com.booktracker.book_tracker.entities.Lista;
import com.booktracker.book_tracker.entities.User;
import com.booktracker.book_tracker.mappers.ListaMapper;
import com.booktracker.book_tracker.repositories.ActividadRepository;
import com.booktracker.book_tracker.repositories.ListaRepository;
import com.booktracker.book_tracker.repositories.UserRepository;
import com.booktracker.book_tracker.exceptions.ResourceNotFoundException;
import com.booktracker.book_tracker.services.ListaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListaServiceImpl implements ListaService {

    private final ListaRepository listaRepository;
    private final UserRepository userRepository;
    private final ActividadRepository actividadRepository;

    @Override
    public ListaResponseDTO crearLista(ListaRequestDTO dto) {

        User usuario = userRepository.findById(dto.userId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        Lista lista = ListaMapper.toEntity(dto, usuario);

        Lista listaGuardada = listaRepository.save(lista);

        Actividad actividad = Actividad.builder()
                .usuario(usuario)
                .tipo("CREO_LISTA")
                .fecha(LocalDateTime.now())
                .build();

        actividadRepository.save(actividad);

        return ListaMapper.toDTO(listaGuardada);
    }

    @Override
    public List<ListaResponseDTO> obtenerTodas() {
        return listaRepository.findAll()
                .stream()
                .map(ListaMapper::toDTO)
                .toList();
    }

    @Override
    public ListaResponseDTO obtenerPorId(Long id) {

        Lista lista = listaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lista no encontrada"));

        return ListaMapper.toDTO(lista);
    }

    @Override
    public void eliminarLista(Long id) {
        listaRepository.deleteById(id);
    }

    @Override
    public List<ListaResponseDTO> obtenerPorUsuario(Long usuarioId) {

        return listaRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(ListaMapper::toDTO)
                .toList();
    }
}