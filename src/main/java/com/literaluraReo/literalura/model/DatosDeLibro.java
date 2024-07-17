package com.literaluraReo.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public record DatosDeLibro(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<DatosDeAutor> autor,
        @JsonAlias("languages") List<String> idioma,
        @JsonAlias("download_count") Integer numeroDeDescargas
) {
}
