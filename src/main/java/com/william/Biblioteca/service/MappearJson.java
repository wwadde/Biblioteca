package com.william.Biblioteca.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class MappearJson {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T convertirClase(String json, Class<T> clase) {

        try {

            return mapper.readValue(json, clase);

        } catch (JsonProcessingException e) {
            throw new RuntimeException("No se pudo convertir el Json al objeto de la clase " + clase.getName() + "[ " + e.getMessage() + "] ");
        }

    }

}
