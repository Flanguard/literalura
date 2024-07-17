package com.literaluraReo.literalura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Informacion(
        List<DatosDeLibro> resultados
) {
}
