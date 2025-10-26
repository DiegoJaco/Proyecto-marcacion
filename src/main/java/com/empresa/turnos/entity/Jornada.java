package com.empresa.turnos.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "jornada")
public class Jornada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación con Colaborador
    @ManyToOne
    @JoinColumn(name = "colaborador_id", nullable = false)
    private Colaborador colaborador;

    // Relación con Turno
    @ManyToOne
    @JoinColumn(name = "turno_id", nullable = false)
    private Turno turno;

    @Column(nullable = false)
    private LocalDate fecha;

    private LocalTime horaEntrada;
    private LocalTime horaSalida;
    private Integer minutosTardanza = 0;
    private Integer minutosExtra = 0;

    @Column(length = 20)
    private String estado; // PLANIFICADA, EN_PROCESO, CERRADA

    // ===== Getters & Setters =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Colaborador getColaborador() { return colaborador; }
    public void setColaborador(Colaborador colaborador) { this.colaborador = colaborador; }

    public Turno getTurno() { return turno; }
    public void setTurno(Turno turno) { this.turno = turno; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public LocalTime getHoraEntrada() { return horaEntrada; }
    public void setHoraEntrada(LocalTime horaEntrada) { this.horaEntrada = horaEntrada; }

    public LocalTime getHoraSalida() { return horaSalida; }
    public void setHoraSalida(LocalTime horaSalida) { this.horaSalida = horaSalida; }

    public Integer getMinutosTardanza() { return minutosTardanza; }
    public void setMinutosTardanza(Integer minutosTardanza) { this.minutosTardanza = minutosTardanza; }

    public Integer getMinutosExtra() { return minutosExtra; }
    public void setMinutosExtra(Integer minutosExtra) { this.minutosExtra = minutosExtra; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}


