package com.appweb.recetas_backend.service;

import java.util.List;
import java.util.Optional;

import com.appweb.recetas_backend.model.dto.ResponseModel;
import com.appweb.recetas_backend.model.entitites.Usuario;

public interface UsuarioService {
    List<Usuario> getAllUsuarios();
    Optional<Usuario> getUsuarioById(Integer id);
    ResponseModel validarUsuarioPorEmail(String email);
    ResponseModel createUsuario(Usuario usuario);
    ResponseModel updateUsuario(Integer id, Usuario usuario);
    ResponseModel deleteUsuario(Integer id);
    ResponseModel validarLogin(String email, String contrasena);
}
