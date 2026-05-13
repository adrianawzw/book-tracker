package com.booktracker.book_tracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.booktracker.book_tracker.entities.Libro;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
    Libro findByIsbn(String isbn);

    List<Libro> findByTituloContainingIgnoreCase(String titulo);

    List<Libro> findByAutorContainingIgnoreCase(String autor);
}
