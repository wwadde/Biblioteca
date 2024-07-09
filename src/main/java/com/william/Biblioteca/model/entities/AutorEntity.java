package com.william.Biblioteca.model.entities;


import com.william.Biblioteca.model.dtos.AutorDTO;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Autores")
public class AutorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nombre;

    private Integer fechaNacimiento;

    private Integer fechaFallecimiento;

    // mappedBy=BookEntity_.AUTOR
    @OneToMany(mappedBy = "autor", fetch = FetchType.EAGER)
    private Set<LibroEntity> libros;

    public AutorEntity() {
    }

    public AutorEntity(AutorDTO autor){

        this.nombre = autor.nombre();
        this.fechaNacimiento = autor.fechaNacimiento();
        this.fechaFallecimiento = autor.fechaFallecimiento();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Integer fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getFechaFallecimiento() {
        return fechaFallecimiento;
    }

    public void setFechaFallecimiento(Integer fechaFallecimiento) {
        this.fechaFallecimiento = fechaFallecimiento;
    }

    public Set<LibroEntity> getLibros() {
        return libros;
    }

    public void setLibros(Set<LibroEntity> libros) {
        this.libros = libros;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AutorEntity that = (AutorEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(nombre, that.nombre) && Objects.equals(fechaNacimiento, that.fechaNacimiento) && Objects.equals(fechaFallecimiento, that.fechaFallecimiento) && Objects.equals(libros, that.libros);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, fechaNacimiento, fechaFallecimiento, libros);
    }

    @Override
    public String toString() {
        return
                "Nombre='" + nombre + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", fechaFallecimiento=" + fechaFallecimiento;
    }
}
