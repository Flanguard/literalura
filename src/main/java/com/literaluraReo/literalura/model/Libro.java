package com.literaluraReo.literalura.model;


import jakarta.persistence.*;

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
    private Integer numeroDeDesdargas;

    public Libro(){}

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

    public Integer getNumeroDeDesdargas() {
        return numeroDeDesdargas;
    }

    public void setNumeroDeDesdargas(Integer numeroDeDesdargas) {
        this.numeroDeDesdargas = numeroDeDesdargas;
    }

    @Override
    public String toString() {
        return "    Libro{" +
                ", titulo='" + titulo + '\'' +
                ", author=" + author.getNombre() +
                ", lenguaje='" + lenguaje + '\'' +
                ", numeroDeDesdargas=" + numeroDeDesdargas;
    }
}
