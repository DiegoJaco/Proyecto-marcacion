package com.empresa.turnos.service;

import com.empresa.turnos.entity.FaltaTardanza;
import com.empresa.turnos.enums.TipoFaltaTardanza;

import java.time.LocalDate;

public interface FaltaTardanzaService {
    FaltaTardanza registrar(Long colaboradorId, LocalDate fecha, TipoFaltaTardanza tipo, Integer minutos, String observacion);
}

