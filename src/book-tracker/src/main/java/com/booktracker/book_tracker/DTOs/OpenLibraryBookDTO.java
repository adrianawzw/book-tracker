package com.booktracker.book_tracker.DTOs;

public record OpenLibraryBookDTO(
        String titulo,
        String autor,
        String apiId,
        String coverUrl,
        String anioPublicacion) {
}