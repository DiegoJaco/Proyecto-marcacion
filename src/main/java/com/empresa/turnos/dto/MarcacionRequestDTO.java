package com.empresa.turnos.dto;

import java.time.LocalDateTime;
import com.empresa.turnos.enums.OrigenMarcacion;
import com.empresa.turnos.enums.TipoMarcacion;

public class MarcacionRequestDTO {
    private Long colaboradorId;
    private LocalDateTime fechaHora;
    private TipoMarcacion tipo;
    private OrigenMarcacion origen = OrigenMarcacion.WEB;

    public Long getColaboradorId() { return colaboradorId; }
    public void setColaboradorId(Long colaboradorId) { this.colaboradorId = colaboradorId; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }
    public TipoMarcacion getTipo() { return tipo; }
    public void setTipo(TipoMarcacion tipo) { this.tipo = tipo; }
    public OrigenMarcacion getOrigen() { return origen; }
    public void setOrigen(OrigenMarcacion origen) { this.origen = origen; }
}
