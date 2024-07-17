package com.literaluraReo.literalura.Services;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
