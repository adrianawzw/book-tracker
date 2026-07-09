package com.booktracker.book_tracker.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {
	@GetMapping("/")
    public String home() {
        return "Book Tracker API funcionando correctamente";
    }
}