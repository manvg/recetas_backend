package com.appweb.recetas_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.appweb.recetas_backend.model.dto.AuthResponse;
import com.appweb.recetas_backend.model.dto.LoginDto;
import com.appweb.recetas_backend.model.dto.ResponseModel;
import com.appweb.recetas_backend.service.AuthenticationService;
import com.appweb.recetas_backend.service.UsuarioService;

@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginDto loginDto) {
        try {
            String email = loginDto.getEmail();
            String password = loginDto.getContrasena();

            //Validar contra base de datos email y contraseña
            ResponseModel response = usuarioService.validarLogin(email, password);

            if (response.getStatus()) {
                String token = authenticationService.authenticate(email, password);
                AuthResponse authResponse = new AuthResponse(true, token, "Autenticación exitosa");

                return ResponseEntity.ok(authResponse);
            } else {
                AuthResponse authResponse = new AuthResponse(false, null, "Usuario y contraseña inválidos");

                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(authResponse);
            }
           
        } catch (Exception e) {
            AuthResponse authResponse = new AuthResponse(false, null, "Error de autenticación");
            
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(authResponse);
        }
    }
}