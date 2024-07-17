package com.literaluraReo.literalura.model;


import jakarta.persistence.*;

import java.util.Optional;

@Entity
@Table(name="libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Author author;
    private String lenguaje;
    private Integer numeroDeDescargas;

    public Libro(){}

    public Libro(DatosDeLibro datosLibro) {
        this.titulo = datosLibro.titulo();
        Optional<DatosDeAutor> autor = datosLibro.autor().stream()
                .findFirst();
        if (autor.isPresent()) {
            this.author = new Author(autor.get());
        } else {
            System.out.println("No se a encontrado el autor");
        }

        this.lenguaje = datosLibro.idioma().get(0);
        this.numeroDeDescargas = datosLibro.numeroDeDescargas();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    public Integer getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Integer numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    @Override
    public String toString() {
        return "    Libro{" +
                ", titulo='" + titulo + '\'' +
                ", author=" + author.getNombre() +
                ", lenguaje='" + lenguaje + '\'' +
                ", numeroDeDesdargas=" + numeroDeDescargas;
    }
}
