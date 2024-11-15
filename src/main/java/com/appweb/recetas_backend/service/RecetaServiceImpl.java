package com.appweb.recetas_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appweb.recetas_backend.model.dto.ResponseModel;
import com.appweb.recetas_backend.model.entitites.Receta;
import com.appweb.recetas_backend.repository.RecetaRepository;

@Service
public class RecetaServiceImpl implements RecetaService {
    @Autowired
    private RecetaRepository recetaRepository;

    //---------GET---------//
    @Override
    public List<Receta> getAllRecetas(){
        return recetaRepository.findAll();
    }

    @Override
    public Optional<Receta> getRecetaById(Integer id){
        return recetaRepository.findById(id);
    }

    //---------POST---------//
    @Override
    public ResponseModel createReceta(Receta receta){
        ResponseModel response;
        var nuevoReceta = recetaRepository.save(receta);
        if (nuevoReceta.getIdReceta() > 0) {
            response = new ResponseModel(true, "Receta creada con éxito. Id: " + receta.getIdReceta());
        }else{
            response = new ResponseModel(false, "Se ha producido un error al intentar crear la receta.");
        }
        return response;
    }

    //---------PUT---------//
    @Override
    public ResponseModel updateReceta(Integer id, Receta objReceta) {
        ResponseModel response;
        var recetaExiste = recetaRepository.findById(id);
        if (recetaExiste.isPresent()) {
            Receta receta = recetaExiste.get();
            receta.setNombre(objReceta.getNombre());
            receta.setDescripcion(objReceta.getDescripcion());
            receta.setIngredientes(objReceta.getIngredientes());
            receta.setInstrucciones(objReceta.getInstrucciones());
            receta.setTipo_cocina(objReceta.getTipo_cocina());
            receta.setPais_origen(objReceta.getPais_origen());
            receta.setTiempo_coccion(objReceta.getTiempo_coccion());
            receta.setDificultad(objReceta.getDificultad());
            receta.setIdReceta(id);
            
            // Actualizar receta
            recetaRepository.save(receta);
            response = new ResponseModel(true, "Receta actualizada con éxito. Id: " + id);
        } else {
            response = new ResponseModel(false, "Receta no encontrada. Id: " + id);
        }
        return response;
    }
    

    //---------DELETE---------//
    @Override
    public ResponseModel deleteReceta(Integer id){
        if (recetaRepository.existsById(id)) {
            recetaRepository.deleteById(id);
            return new ResponseModel(true, "Receta eliminado con éxito");
        }else{
            return new ResponseModel(false, "El receta ingresado no existe");
        }
    }

    @Override
    public ResponseModel validarRecetaPorNombre(String nombre){
        String message = "";
        boolean status = false;

        var existeEmail = recetaRepository.findBynombre(nombre);
        if (!existeEmail.isEmpty()) {
            message = "Ya existe un receta con el nomre '" + nombre+ "'";
        }else{
            message = "Puede continuar con la creación de la receta.";
            status = true;
        }
        return new ResponseModel(status, message);
    }

}


