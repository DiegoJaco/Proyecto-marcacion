package com.empresa.turnos.dto;

import java.time.LocalDateTime;

public record MarcacionRequest(
        Long colaboradorId,
        LocalDateTime fechaHora,
        String tipo,    // ENTADA/SALIDA 
        String origen   // SISTEMA/
) {}

