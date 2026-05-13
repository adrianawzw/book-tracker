package com.booktracker.book_tracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booktracker.book_tracker.entities.Actividad;

@Repository
public interface ActividadRepository extends JpaRepository<Actividad, Long> {
    List<Actividad> findByUsuarioId(Long usuarioId);

    List<Actividad> findByOrderByFechaDesc();
}
