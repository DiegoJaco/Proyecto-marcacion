package com.empresa.turnos.dto;

import com.empresa.turnos.enums.EstadoHoraExtra;

public class HoraExtraDecisionDTO {
    private Integer minutosAprobados;
    private EstadoHoraExtra estado; // APROBADO o RECHAZADO
    public Integer getMinutosAprobados() { return minutosAprobados; }
    public void setMinutosAprobados(Integer minutosAprobados) { this.minutosAprobados = minutosAprobados; }
    public EstadoHoraExtra getEstado() { return estado; }
    public void setEstado(EstadoHoraExtra estado) { this.estado = estado; }
}
