package com.appweb.recetas_backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appweb.recetas_backend.model.entitites.Receta;
import com.appweb.recetas_backend.service.RecetaService;
import com.appweb.recetas_backend.config.Constants;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/recetas")
public class RecetaController {
    @Autowired
    private RecetaService recetaService;

    //---------MÉTODOS GET---------//
    @GetMapping
    public List<Receta> getAllRecetas() {
        List<Receta> recetas = recetaService.getAllRecetas();
        return recetas;
    }

    @GetMapping("/{id}")
    public Optional<Receta> getRecetaById(@PathVariable Integer id){
        var receta = recetaService.getRecetaById(id);
        return receta;
    }

    //---------MÉTODOS POST---------//
    @PostMapping
    public ResponseEntity<Object> createReceta(@RequestHeader(value = "Authorization", required = false) String token,@RequestBody @Valid Receta receta) {

        // Validar que el token esté presente
        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no válido o ausente");
        }

        // Extraer el token real quitando el prefijo "Bearer "
        String actualToken = token.substring(7);

        // Verificar el token (ejemplo de validación con un servicio externo o un método interno)
        boolean isTokenValid = Constants.validateToken(actualToken);
        if (!isTokenValid) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido");
        }

        // Lógica original de creación de receta
        var validacionNombre = recetaService.validarRecetaPorNombre(receta.getNombre());
        if (!validacionNombre.getStatus()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validacionNombre);
        }

        var response = recetaService.createReceta(receta);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //---------MÉTODOS PUT---------//
    //Actualizar receta
    @PutMapping("/{id}")
    public ResponseEntity<Object>  updateReceta(@PathVariable Integer id, @RequestBody @Valid Receta receta){
        var response = recetaService.updateReceta(id, receta);
        if (response.getStatus()) {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    //---------MÉTODOS DELETE---------//
    //Eliminar receta
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteReceta(@PathVariable Integer id){
        var response = recetaService.deleteReceta(id);
        if (!response.getStatus()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
