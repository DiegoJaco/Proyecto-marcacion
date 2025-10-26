package com.empresa.turnos.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record FaltaTardanzaRequest(
        @NotNull Long colaboradorId,
        @NotNull String tipo,          // FALTA, TARDANZA, SALIDA_ANTICIPADA
        @NotNull LocalDate fecha,
        Integer minutos,
        String observacion
) {}
