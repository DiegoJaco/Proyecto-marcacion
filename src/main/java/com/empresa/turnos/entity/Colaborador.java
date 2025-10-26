package com.empresa.turnos.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "colaborador", indexes = {
        @Index(name = "idx_colab_documento", columnList = "documento", unique = true)
})
public class Colaborador {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 120, nullable = false)
    private String nombres;

    @Column(length = 120, nullable = false)
    private String apellidos;

    @Column(length = 20, unique = true)
    private String documento;

    private LocalDate fechaIngreso;

    private Boolean activo;

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }
    public LocalDate getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(LocalDate fechaIngreso) { this.fechaIngreso = fechaIngreso; }
    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }
}

