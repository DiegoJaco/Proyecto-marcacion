package com.empresa.turnos.service;

import java.time.LocalDate;
import com.empresa.turnos.entity.Jornada;

public interface JornadaService {
    Jornada obtenerOCrear(Long colaboradorId, LocalDate fecha, Long turnoIdSugerido);
    Jornada cerrarSiCorresponde(Long colaboradorId, LocalDate fecha);
}

