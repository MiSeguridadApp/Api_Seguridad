package com.apisecurityapp.apisecurityapp.repository;

import com.apisecurityapp.apisecurityapp.model.Organizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizacionRepository extends JpaRepository<Organizacion, Long> {

    // Método para encontrar una organización por su nombre (si es necesario)
    Optional<Organizacion> findByNombre(String nombre);

    // Método para encontrar una organización por su ID (esto ya lo hace JpaRepository)
    Optional<Organizacion> findById(Long id);
}