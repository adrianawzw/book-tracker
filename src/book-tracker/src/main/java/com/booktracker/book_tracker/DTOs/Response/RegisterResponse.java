package com.booktracker.book_tracker.DTOs.Response;

public record RegisterResponse(
    String email,
    String status,
    String message
) {

}
