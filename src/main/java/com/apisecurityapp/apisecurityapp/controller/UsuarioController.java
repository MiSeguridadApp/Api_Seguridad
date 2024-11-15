package com.apisecurityapp.apisecurityapp.controller;

import com.apisecurityapp.apisecurityapp.model.Organizacion;
import com.apisecurityapp.apisecurityapp.model.Usuario;
import com.apisecurityapp.apisecurityapp.repository.UsuarioRepository;
import com.apisecurityapp.apisecurityapp.repository.OrganizacionRepository;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;
    private final OrganizacionRepository organizacionRepository;

    // Constructor
    public UsuarioController(UsuarioRepository usuarioRepository, OrganizacionRepository organizacionRepository) {
        this.usuarioRepository = usuarioRepository;
        this.organizacionRepository = organizacionRepository;
    }

    @PostMapping
    public ResponseEntity<String> agregarUsuario(@RequestBody Usuario usuario) {
        // Verificar si el email ya existe en la base de datos
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            return new ResponseEntity<>("El correo electrónico ya está registrado.", HttpStatus.BAD_REQUEST);
        }

        // Validar que el email no sea nulo y cumpla con el formato
        if (usuario.getEmail() == null || !validarEmail(usuario.getEmail())) {
            return new ResponseEntity<>("El correo electrónico no es válido.", HttpStatus.BAD_REQUEST);
        }

        // Validar la contraseña
        if (usuario.getContrasena() == null || !validarContrasena(usuario.getContrasena())) {
            return new ResponseEntity<>("La contraseña no cumple con los requisitos.", HttpStatus.BAD_REQUEST);
        }

        // Verificar que la organización esté presente en el cuerpo de la solicitud
        if (usuario.getOrganizacion() == null || usuario.getOrganizacion().getIdorganizacion() == null) {
            return new ResponseEntity<>("El ID de organización no puede ser nulo.", HttpStatus.BAD_REQUEST);
        }

        // Buscar la organización en la base de datos
        Organizacion organizacion = organizacionRepository.findById(usuario.getOrganizacion().getIdorganizacion()).orElse(null);
        if (organizacion == null) {
            return new ResponseEntity<>("La organización no existe.", HttpStatus.BAD_REQUEST);
        }

        // Asignar la organización al usuario
        usuario.setOrganizacion(organizacion);
        usuario.setFechaCreacion(new Date());  // Establecer la fecha de creación

        // Guardar el usuario en la base de datos
        usuarioRepository.save(usuario);

        return new ResponseEntity<>("Usuario creado exitosamente.", HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerTodosLosUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return ResponseEntity.ok(usuarios);
    }

    // Métodos de validación

    private boolean validarEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&-]+(?:\\.[a-zA-Z0-9_+&-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean validarContrasena(String contrasena) {
        if (contrasena.length() < 8) {
            return false;
        }

        String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
        return contrasena.matches(pattern);
    }

    private boolean contieneLetraMayuscula(String contrasena) {
        return contrasena.matches(".*[A-Z].*");
    }

    private boolean contieneLetraMinuscula(String contrasena) {
        return contrasena.matches(".*[a-z].*");
    }

    private boolean contieneCaracterEspecial(String contrasena) {
        return contrasena.matches(".*[@#$%^&+=!].*");
    }

    // Método para obtener el usuario autenticado (si es necesario)
    private String getAuthenticatedUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return userDetails.getUsername();
        }
        return null;
    }
}