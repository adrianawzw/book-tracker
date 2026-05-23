package com.booktracker.book_tracker.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.booktracker.book_tracker.DTOs.OpenLibraryBookDTO;
import com.booktracker.book_tracker.services.OpenLibraryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/openlibrary")
@RequiredArgsConstructor
public class OpenLibraryController {

    private final OpenLibraryService openLibraryService;

    @GetMapping("/buscar")
    public List<OpenLibraryBookDTO> buscarLibros(
            @RequestParam String titulo
    ) {

        return openLibraryService.buscarLibros(titulo);
    }
}