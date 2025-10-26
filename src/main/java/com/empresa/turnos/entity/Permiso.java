package com.empresa.turnos.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "permiso")
public class Permiso {

    public enum Tipo { VACACIONES, LICENCIA, MEDICA, OTRO }
    public enum Estado { PENDIENTE, APROBADO, RECHAZADO }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Colaborador colaborador;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Tipo tipo;

    @Column(nullable = false)
    private LocalDate fechaInicio;

    @Column(nullable = false)
    private LocalDate fechaFin;

    @Column(length = 255)
    private String motivo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Estado estado = Estado.PENDIENTE;

    // Setters de conveniencia (aceptan String desde el controller/DTO)
    public void setTipo(String tipo) {
        this.tipo = Tipo.valueOf(tipo.toUpperCase());
    }
    public void setEstado(String estado) {
        this.estado = Estado.valueOf(estado.toUpperCase());
    }
}
