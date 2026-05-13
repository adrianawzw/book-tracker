package com.booktracker.book_tracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booktracker.book_tracker.entities.Resena;

@Repository
public interface ReseñaRepository extends JpaRepository<Resena, Long> {
    List<Resena> findByUsuarioId(Long usuarioId);

    List<Resena> findByLibroId(Long libroId);

    Resena findByUsuarioIdAndLibroId(Long usuarioId, Long libroId);

}
