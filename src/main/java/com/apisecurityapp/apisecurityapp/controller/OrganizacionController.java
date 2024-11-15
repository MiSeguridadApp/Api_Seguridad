package com.apisecurityapp.apisecurityapp.controller;

import com.apisecurityapp.apisecurityapp.model.Organizacion;
import com.apisecurityapp.apisecurityapp.repository.OrganizacionRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/organizaciones")
@CrossOrigin
public class OrganizacionController {

    private final OrganizacionRepository organizacionRepository;

    // Constructor
    @Autowired
    public OrganizacionController(OrganizacionRepository organizacionRepository) {
        this.organizacionRepository = organizacionRepository;
    }

    // POST: Crear una nueva organización
    @PostMapping
    public ResponseEntity<String> registrarOrganizacion(@RequestBody Organizacion organizacion) {
        // Validar que el nombre no esté vacío
        if (organizacion.getNombre() == null || organizacion.getNombre().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El nombre de la organización es obligatorio.");
        }

        // Validar que el tipo no esté vacío
        if (organizacion.getTipo() == null || organizacion.getTipo().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El tipo de la organización es obligatorio.");
        }

        // Verificar si la organización ya existe por nombre
        Optional<Organizacion> organizacionExistente = organizacionRepository.findByNombre(organizacion.getNombre());
        if (organizacionExistente.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ya existe una organización con este nombre.");
        }

        // Guardar la nueva organización
        organizacionRepository.save(organizacion);

        // Responder con éxito
        Map<String, String> response = new HashMap<>();
        response.put("message", "Organización registrada correctamente.");
        Gson gson = new Gson();
        String responseBody = gson.toJson(response);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    // GET: Obtener todas las organizaciones
    @GetMapping
    public ResponseEntity<List<Organizacion>> obtenerTodasLasOrganizaciones() {
        List<Organizacion> organizaciones = organizacionRepository.findAll();
        return ResponseEntity.ok(organizaciones);
    }

    // GET: Obtener una organización por ID
    @GetMapping("/{id}")
    public ResponseEntity<Organizacion> obtenerOrganizacionPorId(@PathVariable("id") Long id) {
        Optional<Organizacion> organizacion = organizacionRepository.findById(id);
        if (organizacion.isPresent()) {
            return ResponseEntity.ok(organizacion.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
