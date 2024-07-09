package com.william.Biblioteca.repository;

import com.william.Biblioteca.model.entities.LibroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface LibroRepository extends JpaRepository<LibroEntity, Long> {
    Optional<LibroEntity> findByTitulo(String titulo);
}
