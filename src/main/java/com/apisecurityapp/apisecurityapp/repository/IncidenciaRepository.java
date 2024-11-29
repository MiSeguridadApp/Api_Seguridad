package com.apisecurityapp.apisecurityapp.repository;

import com.apisecurityapp.apisecurityapp.model.Incidencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface IncidenciaRepository extends JpaRepository<Incidencia, Long> {
    @Procedure(name = "f_insertar_incidencia1")
    public String f_insertar_incidencia1(
            @Param("in_idusuario") int id_usuario,
            @Param("in_ubicacion") String in_ubicacion,
            @Param("in_estado") int in_estado,
            @Param("in_tipo") String in_tipo,
            @Param("in_latitud") double in_latitud,
            @Param("in_longitud") double in_longitud
    );


}