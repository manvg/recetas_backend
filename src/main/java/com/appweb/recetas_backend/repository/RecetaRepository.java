package com.appweb.recetas_backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appweb.recetas_backend.model.entitites.Receta;

public interface RecetaRepository extends JpaRepository<Receta, Integer> {
   Optional<Receta> findBynombre(String nombre);
}