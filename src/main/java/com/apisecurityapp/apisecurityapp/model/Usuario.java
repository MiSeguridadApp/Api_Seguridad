package com.apisecurityapp.apisecurityapp.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idusuario")
    private Long id;  // La clave primaria

    @ManyToOne
    @JoinColumn(name = "idorganizacion", referencedColumnName = "idorganizacion", nullable = false)
    private Organizacion organizacion;  // Relación con la entidad 'Organizacion'

    @Column(name = "nombre", length = 100)
    private String nombre;

    @Column(name = "apellidos", length = 255)
    private String apellidos;

    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "contrasena", length = 255)
    private String contrasena;

    @Column(name = "perfil")
    private Integer perfil; // Estado del perfil (1: Admin, 2: Usuario)

    @Column(name = "fecha_creacion", columnDefinition = "DATE DEFAULT CURRENT_DATE")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Organizacion getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(Organizacion organizacion) {
        this.organizacion = organizacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Integer getPerfil() {
        return perfil;
    }

    public void setPerfil(Integer perfil) {
        this.perfil = perfil;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
