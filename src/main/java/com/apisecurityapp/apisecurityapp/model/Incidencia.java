package com.apisecurityapp.apisecurityapp.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "incidencia")
public class Incidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idincidencia")
    private Long id;  // La clave primaria

    @ManyToOne
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario", nullable = false)
    private Usuario usuario;  // Relación con la entidad 'Usuario'

    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @Column(name = "tipo")
    private Integer tipo;

    @Column(name = "ubicacion", columnDefinition = "TEXT")
    private String ubicacion;

    @Column(name = "estado", columnDefinition = "integer default 1")
    private Integer estado;  // Estado de la incidencia (1: Registrado, 2: Atendido, 3: Solucionado)

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
}
