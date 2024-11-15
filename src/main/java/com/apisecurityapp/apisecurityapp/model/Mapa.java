package com.apisecurityapp.apisecurityapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "mapa")
public class Mapa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;  // La clave primaria

    @ManyToOne
    @JoinColumn(name = "idincidencia", referencedColumnName = "idincidencia", nullable = false)
    private Incidencia incidencia;  // Relación con la entidad 'Incidencia'

    @Column(name = "ubicacion", columnDefinition = "TEXT")
    private String ubicacion;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Incidencia getIncidencia() {
        return incidencia;
    }

    public void setIncidencia(Incidencia incidencia) {
        this.incidencia = incidencia;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
