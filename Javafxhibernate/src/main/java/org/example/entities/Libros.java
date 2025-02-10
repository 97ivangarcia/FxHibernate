package org.example.entities;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Libros")
public class Libros implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String titulo;

    @Column(unique = true, nullable = false)
    private String isbn;

    @Column(nullable = false)
    private String autor;

    @Column
    private String editorial;

    @Column
    private Integer anioPublicacion;

    // Constructor vacío
    public Libros() {}

    // Constructor con parámetros
    public Libros(Integer id, String titulo, String isbn, String autor, String editorial, Integer anioPublicacion) {
        this.id = id;
        this.titulo = titulo;
        this.isbn = isbn;
        this.autor = autor;
        this.editorial = editorial;
        this.anioPublicacion = anioPublicacion;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public Integer getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(Integer anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }
}
