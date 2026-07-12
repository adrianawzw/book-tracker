package com.booktracker.book_tracker.services;

import java.util.List;

import com.booktracker.book_tracker.DTOs.OpenLibraryAuthorDTO;
import com.booktracker.book_tracker.DTOs.OpenLibraryBookDTO;

public interface OpenLibraryService {

    List<OpenLibraryBookDTO> buscarLibros(String titulo, int limit);

    List<OpenLibraryBookDTO> librosPopulares(int limit);

    List<OpenLibraryAuthorDTO> buscarAutores(String nombre, int limit);

    List<OpenLibraryBookDTO> librosPorAutor(String authorKey, int limit);
}