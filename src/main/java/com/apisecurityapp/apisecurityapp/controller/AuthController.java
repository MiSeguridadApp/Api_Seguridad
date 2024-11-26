package com.apisecurityapp.apisecurityapp.controller;

import com.apisecurityapp.apisecurityapp.dto.LoginRequest;
import com.apisecurityapp.apisecurityapp.model.Usuario;
import com.apisecurityapp.apisecurityapp.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;



    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Obtiene el usuario autenticado desde el servicio de detalles de usuario
            Usuario usuario = usuarioRepository.findByEmail(loginRequest.getEmail()).orElse(null);

            if (usuario == null) {
                throw new RuntimeException("Usuario no encontrado");
            }

            Map<String, String> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("email", loginRequest.getEmail());
            response.put("userId", String.valueOf(usuario.getId()));

            return response;
        } catch (AuthenticationException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("status", "failed");
            errorResponse.put("email", loginRequest.getEmail());
            errorResponse.put("contra", loginRequest.getPassword());
            errorResponse.put("message", "Combinación de nombre de usuario/contraseña no válida");
            errorResponse.put("details", e.getMessage());
            return errorResponse;
        }
    }

}