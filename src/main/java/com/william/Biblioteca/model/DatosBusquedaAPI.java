package com.william.Biblioteca.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.william.Biblioteca.model.dtos.LibroDTO;


import java.util.List;


// Clase que recibe los resultados del json para posteriormente mapearlos a las demas clases
@JsonIgnoreProperties(ignoreUnknown = true)
public class DatosBusquedaAPI {

    @JsonAlias("count")
    private int numeroResultados;

    private List<LibroDTO> results;

    public int getNumeroResultados() {
        return numeroResultados;
    }

    public List<LibroDTO> getResults() {
        return results;
    }
}
