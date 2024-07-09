package com.william.Biblioteca.model.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LibroDTO(
        @JsonAlias("authors") List<AutorDTO> autores,

        @JsonAlias("title") String titulo,

        @JsonAlias("subjects") List<String> tematica,

        @JsonAlias("languages") List<String> idiomasDisponibles,

        @JsonAlias("download_count") Integer contadorDescargas
) {
}
