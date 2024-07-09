package com.william.Biblioteca.model.entities;

import com.william.Biblioteca.model.dtos.LibroDTO;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Libros")
public class LibroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private List<String> tematica;

    private List<String> idiomasDisponibles;

    private Integer contadorDescargas;

    @ManyToOne
    private AutorEntity autor;

    public LibroEntity() {
    }

    public LibroEntity(LibroDTO libro) {

        this.titulo = libro.titulo();
        this.tematica = libro.tematica();
        this.idiomasDisponibles = libro.idiomasDisponibles();
        this.contadorDescargas = libro.contadorDescargas();

    }

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

    public List<String> getTematica() {
        return tematica;
    }

    public void setTematica(List<String> tematica) {
        this.tematica = tematica;
    }

    public List<String> getIdiomasDisponibles() {
        return idiomasDisponibles;
    }

    public void setIdiomasDisponibles(List<String> idiomasDisponibles) {
        this.idiomasDisponibles = idiomasDisponibles;
    }

    public Integer getContadorDescargas() {
        return contadorDescargas;
    }

    public void setContadorDescargas(Integer contadorDescargas) {
        this.contadorDescargas = contadorDescargas;
    }

    public AutorEntity getAutor() {
        return autor;
    }

    public void setAutor(AutorEntity autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return
                "Titulo='" + titulo + '\'' +
                        ", Tematica=" + tematica.getFirst() +
                        ", IdiomasDisponibles=" + idiomasDisponibles +
                        ", ContadorDescargas=" + contadorDescargas +
                        ", Autor: " + autor.getNombre();
    }
}
