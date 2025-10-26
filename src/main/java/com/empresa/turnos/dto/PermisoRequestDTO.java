package com.empresa.turnos.dto;

import java.time.LocalDate;
import com.empresa.turnos.enums.EstadoPermiso;

public class PermisoRequestDTO {
    private Long colaboradorId;
    private String tipo; // VACACIONES, LICENCIA, MEDICA, OTRO
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String motivo;
    private EstadoPermiso estado; // opcional en creación (default PENDIENTE)
    // getters/setters
    public Long getColaboradorId() { return colaboradorId; }
    public void setColaboradorId(Long colaboradorId) { this.colaboradorId = colaboradorId; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }
    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
    public EstadoPermiso getEstado() { return estado; }
    public void setEstado(EstadoPermiso estado) { this.estado = estado; }
}

