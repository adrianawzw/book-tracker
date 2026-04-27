package com.booktracker.book_tracker.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/hola")
public class HolaController {
    
    @GetMapping()
    public String saludar() {
        return "Hola!";
    }
    
}
