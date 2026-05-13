package com.booktracker.book_tracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booktracker.book_tracker.entities.User;

@Repository
public interface UsuarioRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByUsername(String username);

    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
