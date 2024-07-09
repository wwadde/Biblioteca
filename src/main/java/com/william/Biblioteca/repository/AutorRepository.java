package com.william.Biblioteca.repository;

import com.william.Biblioteca.model.entities.AutorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<AutorEntity, Long> {

    Optional<AutorEntity> findByNombre(String nombre);

    @Query("SELECT a FROM AutorEntity a WHERE a.fechaNacimiento <= :fecha AND (a.fechaFallecimiento IS NULL OR a.fechaFallecimiento > :fecha)")
    List<AutorEntity> findAutoresVivosEnFecha(Integer fecha);
}

