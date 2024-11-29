package com.apisecurityapp.apisecurityapp.service;

import com.apisecurityapp.apisecurityapp.model.Incidencia;
import com.apisecurityapp.apisecurityapp.model.Usuario;
import com.apisecurityapp.apisecurityapp.repository.IncidenciaRepository;
import com.apisecurityapp.apisecurityapp.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class IncidenciaService {

    @Autowired
    private IncidenciaRepository incidenciaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public String f_insertar_incidencia1(Incidencia incidencia){
        return incidenciaRepository.f_insertar_incidencia1(incidencia.getIdusuario(), incidencia.getUbicacion(),incidencia.getEstado(), incidencia.getTipo(), incidencia.getLatitud(), incidencia.getLongitud());
    }

    public List<Incidencia> obtenerTodasLasIncidencias() {
        return incidenciaRepository.findAll(); // Retorna todas las incidencias
    }

}