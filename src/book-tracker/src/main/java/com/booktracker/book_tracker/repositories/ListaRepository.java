package com.booktracker.book_tracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booktracker.book_tracker.entities.Lista;

public interface ListaRepository  extends JpaRepository<Lista, Long> {
     List<Lista> findByUsuarioId(Long usuarioId);
}
