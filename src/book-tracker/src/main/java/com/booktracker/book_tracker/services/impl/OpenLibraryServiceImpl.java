package com.booktracker.book_tracker.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.web.client.ResourceAccessException;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.booktracker.book_tracker.DTOs.OpenLibraryBookDTO;
import com.booktracker.book_tracker.services.OpenLibraryService;

@Service
public class OpenLibraryServiceImpl implements OpenLibraryService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<OpenLibraryBookDTO> buscarLibros(String titulo) {

        String url = "https://openlibrary.org/search.json?q=" + titulo;

        Map response;

        try {
            response = restTemplate.getForObject(url, Map.class);
        } catch (ResourceAccessException e) {
            throw new RuntimeException("La API de OpenLibrary no está disponible. Intente más tarde.", e);
        }

        if (response == null)
            return new ArrayList<>();

        List<Map<String, Object>> docs = (List<Map<String, Object>>) response.get("docs");

        List<OpenLibraryBookDTO> libros = new ArrayList<>();

        for (int i = 0; i < Math.min(docs.size(), 10); i++) {

            Map<String, Object> libro = docs.get(i);

            String tituloLibro = (String) libro.get("title");

            String autor = "Desconocido";

            if (libro.get("author_name") != null) {
                List<String> autores = (List<String>) libro.get("author_name");

                autor = autores.get(0);
            }

            String apiId = (String) libro.get("key");

            libros.add(
                    new OpenLibraryBookDTO(
                            tituloLibro,
                            autor,
                            apiId));
        }

        return libros;
    }
}