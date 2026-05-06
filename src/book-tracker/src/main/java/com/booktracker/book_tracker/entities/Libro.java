package com.booktracker.book_tracker.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "libros")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "libro_id")
    private Long id;

    @Column(nullable = false, length = 200)
    private String titulo;

    @Column(length = 150)
    private String autor;

    @Column(length = 1000)
    private String descripcion;

    @Column(name = "imagen_url")
    private String imagenUrl;

    @Column(name = "api_id", unique = true)
    private String apiId;

    @Column(name = "fecha_publicacion")
    private String fechaPublicacion;

    @Column(length = 100)
    private String genero;
}
