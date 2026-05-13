package com.booktracker.book_tracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booktracker.book_tracker.entities.LibroEnLista;

@Repository
public interface LibroEnListaRepository extends JpaRepository<LibroEnLista, Long> {
    List<LibroEnLista> findByListaId(Long listaId);

    List<LibroEnLista> findByLibroId(Long libroId);

}
