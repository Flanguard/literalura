package com.literaluraReo.literalura.model;


import jakarta.persistence.*;

@Entity
@Table(name="libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nombre;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Author author;
    private String lenguaje;
    private Integer numeroDeDesdargas;

    public Libro(){}
}
