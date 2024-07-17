package com.literaluraReo.literalura.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="autores")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Integer nacimiento;
    private Integer fallecimiento;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros;

    public Author() {
    }

    public Author(DatosDeAutor datosDeAutor){
        this.nombre = datosDeAutor.nombre();
        this.nacimiento = datosDeAutor.nacimiento();
        this.fallecimiento = datosDeAutor.muerte();
    }

    public String getNombre() {
        return nombre;
    }

    public Long getId() {
        return id;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Integer nacimiento) {
        this.nacimiento = nacimiento;
    }

    public Integer getFallecimiento() {
        return fallecimiento;
    }

    public void setFallecimiento(Integer fallecimiento) {
        this.fallecimiento = fallecimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        libros.forEach(l-> l.setAuthor(this));
        this.libros = libros;
    }

    @Override
    public String toString() {
            return "Author{" +
                "nombre= '" + nombre + '\'' +
                ", nacimiendo= " + nacimiento +
                ", fallecimiento= " + fallecimiento +
                ", libros= " + libros.stream().map(Libro::getTitulo).collect(Collectors.toList()) +
                '}';
    }
}
