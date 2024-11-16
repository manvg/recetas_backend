package com.appweb.recetas_backend.service;
import java.util.List;
import java.util.Optional;

import com.appweb.recetas_backend.model.dto.ResponseModel;
import com.appweb.recetas_backend.model.entitites.Receta;

public interface RecetaService {
    List<Receta> getAllRecetas(String nombre, String tipoCocina, String ingredientes, String paisOrigen, String dificultad);
    Optional<Receta> getRecetaById(Integer id);
    ResponseModel validarRecetaPorNombre(String nombre);
    ResponseModel createReceta(Receta receta);
    ResponseModel updateReceta(Integer id, Receta receta);
    ResponseModel deleteReceta(Integer id);
}
