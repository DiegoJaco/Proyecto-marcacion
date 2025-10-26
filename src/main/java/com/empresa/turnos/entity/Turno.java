package com.empresa.turnos.entity;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "turno")
public class Turno {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 60, nullable = false)
    private String nombre;

    private LocalTime horaInicio;
    private LocalTime horaFin;

    private Integer minutosTolerancia;

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public LocalTime getHoraInicio() { return horaInicio; }
    public void setHoraInicio(LocalTime horaInicio) { this.horaInicio = horaInicio; }
    public LocalTime getHoraFin() { return horaFin; }
    public void setHoraFin(LocalTime horaFin) { this.horaFin = horaFin; }
    public Integer getMinutosTolerancia() { return minutosTolerancia; }
    public void setMinutosTolerancia(Integer minutosTolerancia) { this.minutosTolerancia = minutosTolerancia; }
}

