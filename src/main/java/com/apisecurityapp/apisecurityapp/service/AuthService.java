package com.apisecurityapp.apisecurityapp.service;

import com.apisecurityapp.apisecurityapp.model.Usuario;
import com.apisecurityapp.apisecurityapp.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName(); // Obtener el nombre de usuario directamente
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);
        return usuarioOptional.orElse(null);
    }
}
