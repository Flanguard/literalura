package com.literaluraReo.literalura.principal;

import com.literaluraReo.literalura.Services.ConsumoApi;
import com.literaluraReo.literalura.Services.ConvierteDatos;
import com.literaluraReo.literalura.model.Author;
import com.literaluraReo.literalura.model.DatosDeLibro;
import com.literaluraReo.literalura.model.Informacion;
import com.literaluraReo.literalura.model.Libro;
import com.literaluraReo.literalura.repository.IAuthorRepository;
import com.literaluraReo.literalura.repository.ILibroRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private ConvierteDatos conversor = new ConvierteDatos();

    private ILibroRepository libroRepository;
    private IAuthorRepository autorRepository;

    @Autowired
    public Principal(ILibroRepository libroRepository, IAuthorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public void menu() {
        var opcion = -1;
        do {
            var menu = """              
                    *********************************
                    ************* MENU **************
                    *********************************
                    1- Buscar libro por titulo
                    2- Listar libros registrados
                    3- Listar autores registrados
                    4- Listar autores vivos en un determinado año
                    5- Listar libros por idioma
                    0- Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    try {
                        buscarLibro();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;

                case 2:
                    getLibros();
                    break;
                case 3:
                    getAutores();
                    break;
                case 4:
                    getAutorPorAno();
                    break;
                case 5:
                    getLibroPorIdioma();
                    break;
                case 0:
                    System.out.println("Saliendo del sistema");
                    break;
                default:
                    System.out.println("Opcion invalida intente nuevamente.");
            }

        } while (opcion != 0);
    }

    private void buscarLibro() throws IOException {
        System.out.println("Ingrese el titulo del libro: ");
        var nombreLibro = teclado.nextLine();
        var nombreLibroEncoded = URLEncoder.encode(nombreLibro, StandardCharsets.UTF_8.toString());
        var json = consumoApi.obtenerDatos(URL_BASE + nombreLibroEncoded.replace(" ", "+"));
        List<DatosDeLibro> datosLibro = conversor.obtenerDatos(json, Informacion.class).resultados();
        Optional<DatosDeLibro> libroBuscado = datosLibro.stream()
                .filter(l -> l.titulo().toLowerCase().contains(nombreLibro.toLowerCase()))
                .findFirst();

        if (libroBuscado.isPresent()) {
            var libroEncontrado = libroBuscado.get();

            // Verificacion si el libro ya esta en la base de datos
            Libro libroExistente = libroRepository.findByTituloIgnoreCase(libroEncontrado.titulo());

            if (libroExistente != null) {
                System.out.println("El libro " + libroExistente.getTitulo() + " ya esta registrado");
            } else {
                var libro = new Libro(libroEncontrado);
                libroRepository.save(libro);
                System.out.println(libro);
            }
        } else {
            System.out.println("Libro no encontrado");
        }
    }

    private void getLibros() {
        List<Libro> libros = libroRepository.findAll();
        try {
            if (libros.isEmpty()) {
                System.out.println("No se han encontrado libros registrados");
            } else {
                for (Libro libro : libros) {
                    System.out.println(libro.toString());
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    private void getAutores() {
        List<Author> autores = autorRepository.findAll();
        try {
            if (autores.isEmpty()) {
                System.out.println("No se han encontrado autores registrados");
            } else {
                for (Author autor : autores) {
                    System.out.println(autor.toString());
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    private void getAutorPorAno() {
        System.out.println("Ingrese el año en el que estuvo vivo su autor: ");
        var ano = teclado.nextInt();
        teclado.nextLine();
        List<Author> autores = autorRepository.findByNacimientoLessThanEqualAndFallecimientoGreaterThanEqual(ano, ano);
        for (Author autor : autores) {
            System.out.println(autor.toString());
        }
    }

    private void getLibroPorIdioma() {
        System.out.println("""
            Ingrese el idioma para buscar los libros
            es - español
            en - ingles
            fr - frances
            pt - portugues
            """);
        String idioma = teclado.nextLine();
        List<Libro> libros = libroRepository.findByLenguaje(idioma);
        for (Libro libro : libros) {
            System.out.println(libro.toString());
        }
    }
}
