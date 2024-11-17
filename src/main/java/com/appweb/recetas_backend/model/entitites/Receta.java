package com.appweb.recetas_backend.model.entitites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "receta")
public class Receta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_receta")
    private int idReceta;

    @Column(name = "nombre")
    @NotNull
    @Size(min = 2, max = 100, message = "Debe tener entre 2 y 100 caracteres")
    private String nombre;

    @Column(name = "descripcion")
    @NotNull
    @Size(min = 2, max = 100, message = "Debe tener entre 2 y 250 caracteres")
    private String descripcion;

    @Column(name = "ingredientes")
    @NotNull
    @Size(min = 2, max = 500, message = "Debe tener entre 2 y 500 caracteres")
    private String ingredientes;

    @Column(name = "instrucciones")
    @NotNull
    @Size(min = 2, max = 500, message = "Debe tener entre 2 y 500 caracteres")
    private String instrucciones;


    @Column(name = "tipo_cocina")
    @NotNull
    @Size(min = 2, max = 150, message = "Debe tener entre 2 y 150 caracteres")
    private String tipoCocina;

    @Column(name = "pais_origen")
    @NotNull
    @Size(min = 2, max = 150, message = "Debe tener entre 2 y 150 caracteres")
    private String paisOrigen;

    @Column(name = "tiempo_coccion")
    @NotNull
    @Size(min = 2, max = 30, message = "Debe tener entre 2 y 30 caracteres")
    private String tiempoCoccion;

    @Column(name = "dificultad")
    @NotNull
    @Size(min = 2, max = 50, message = "Debe tener entre 2 y 50 caracteres")
    private String dificultad;

    @Column(name = "url_imagen")
    private String urlImagen;

    @Column(name = "url_video")
    private String urlVideo; 
    
    public int getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(int idReceta) {
        this.idReceta = idReceta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(String instrucciones) {
        this.instrucciones = instrucciones;
    }

    public String getTipoCocina() {
        return tipoCocina;
    }

    public void setTipoCocina(String tipoCocina) {
        this.tipoCocina = tipoCocina;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    public String getTiempoCoccion() {
        return tiempoCoccion;
    }

    public void setTiempoCoccion(String tiempoCoccion) {
        this.tiempoCoccion = tiempoCoccion;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }
}
