package com.apisecurityapp.apisecurityapp.controller;

import com.apisecurityapp.apisecurityapp.model.Incidencia;
import com.apisecurityapp.apisecurityapp.service.IncidenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/incidencias")
public class IncidenciaController {

    @Autowired
    private IncidenciaService incidenciaService;

    @PostMapping
    public ResponseEntity<?> crearIncidencia(@RequestBody Incidencia incidencia) {
        System.out.println(incidencia.toString());
        String nuevaIncidencia = incidenciaService.f_insertar_incidencia1(incidencia);
        return new ResponseEntity<>(nuevaIncidencia, HttpStatus.CREATED);
    }
}