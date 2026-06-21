package com.booktracker.book_tracker.DTOs.Request;

public record RegisterRequest(
    String nombres,
    String apellidos,
    String email,
    String clave
) {

}
