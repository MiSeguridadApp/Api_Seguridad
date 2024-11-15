package com.apisecurityapp.apisecurityapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "organizacion")
public class Organizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idorganizacion")
    private Long idorganizacion;

    @Column(name = "nombre", length = 100)
    private String nombre;

    @Column(name = "tipo", length = 100)
    private String tipo;

    // Getters y Setters


    public Long getIdorganizacion() {
        return idorganizacion;
    }

    public void setIdorganizacion(Long idorganizacion) {
        this.idorganizacion = idorganizacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
