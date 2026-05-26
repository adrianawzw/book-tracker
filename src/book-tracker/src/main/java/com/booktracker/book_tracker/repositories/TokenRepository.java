package com.booktracker.book_tracker.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booktracker.book_tracker.entities.Token;
import java.util.List;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByToken(String token);
    List<Token> findAllByUser_Id(Long userId);
}
