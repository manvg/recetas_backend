package com.appweb.recetas_backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.appweb.recetas_backend.model.entitites.Receta;

public interface RecetaRepository extends JpaRepository<Receta, Integer> {
   Optional<Receta> findBynombre(String nombre);
    @Query("SELECT r FROM Receta r WHERE " +
    "(r.nombre LIKE %:nombre% OR :nombre IS NULL) AND " +
    "(r.descripcion LIKE %:descripcion% OR :descripcion IS NULL) AND " +
    "(r.tipoCocina LIKE %:tipoCocina% OR :tipoCocina IS NULL) AND " +
    "(r.paisOrigen LIKE %:paisOrigen% OR :paisOrigen IS NULL) AND " +
    "(r.dificultad LIKE %:dificultad% OR :dificultad IS NULL)")
    List<Receta> buscarRecetas(
    @Param("nombre") String nombre,
    @Param("descripcion") String descripcion,
    @Param("tipoCocina") String tipoCocina,
    @Param("paisOrigen") String paisOrigen,
    @Param("dificultad") String dificultad
    );
}