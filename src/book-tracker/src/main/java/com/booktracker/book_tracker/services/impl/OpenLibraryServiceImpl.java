package com.booktracker.book_tracker.services.impl;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.booktracker.book_tracker.DTOs.OpenLibraryBookDTO;
import com.booktracker.book_tracker.services.OpenLibraryService;

@Service
public class OpenLibraryServiceImpl implements OpenLibraryService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<OpenLibraryBookDTO> buscarLibros(String titulo, int limit) {
        String query = URLEncoder.encode(titulo, StandardCharsets.UTF_8);
        String url = "https://openlibrary.org/search.json?q=" + query + "&limit=" + limit;
        return ejecutarBusqueda(url, limit);
    }

    @Override
    public List<OpenLibraryBookDTO> librosPopulares(int limit) {
        String url = "https://openlibrary.org/search.json?q=subject:fiction&sort=rating&limit=" + limit;
        return ejecutarBusqueda(url, limit);
    }

    @SuppressWarnings("unchecked")
    private List<OpenLibraryBookDTO> ejecutarBusqueda(String url, int limit) {
        Map<String, Object> response;

        try {
            response = restTemplate.getForObject(url, Map.class);
        } catch (ResourceAccessException e) {
            throw new RuntimeException("La API de OpenLibrary no está disponible. Intente más tarde.", e);
        }

        if (response == null || response.get("docs") == null) {
            return new ArrayList<>();
        }

        List<Map<String, Object>> docs = (List<Map<String, Object>>) response.get("docs");
        List<OpenLibraryBookDTO> libros = new ArrayList<>();

        for (int i = 0; i < Math.min(docs.size(), limit); i++) {
            Map<String, Object> libro = docs.get(i);

            String tituloLibro = (String) libro.get("title");

            String autor = "Desconocido";
            if (libro.get("author_name") != null) {
                List<String> autores = (List<String>) libro.get("author_name");
                if (!autores.isEmpty())
                    autor = autores.get(0);
            }

            String apiId = (String) libro.get("key");

            String coverUrl = "";
            Object coverId = libro.get("cover_i");
            if (coverId != null)
                coverUrl = "https://covers.openlibrary.org/b/id/" + coverId + "-M.jpg";

            String anioPublicacion = "";
            Object anio = libro.get("first_publish_year");
            if (anio != null)
                anioPublicacion = String.valueOf(anio);

            libros.add(new OpenLibraryBookDTO(tituloLibro, autor, apiId, coverUrl, anioPublicacion));
        }

        return libros;
    }
}