package com.apisecurityapp.apisecurityapp.service;

import com.apisecurityapp.apisecurityapp.model.Incidencia;
import com.apisecurityapp.apisecurityapp.model.Usuario;
import com.apisecurityapp.apisecurityapp.repository.IncidenciaRepository;
import com.apisecurityapp.apisecurityapp.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class IncidenciaService {

    @Autowired
    private IncidenciaRepository incidenciaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public String f_insertar_incidencia1(Incidencia incidencia){
        return incidenciaRepository.f_insertar_incidencia1(incidencia.getIdusuario(), incidencia.getUbicacion(),incidencia.getEstado(), incidencia.getTipo());
    }
}