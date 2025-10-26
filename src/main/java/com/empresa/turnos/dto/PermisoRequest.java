package com.empresa.turnos.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record PermisoRequest(
        @NotNull Long colaboradorId,
        @NotNull String tipo,          // VACACIONES, LICENCIA, MEDICA, OTRO
        @NotNull LocalDate fechaInicio,
        @NotNull LocalDate fechaFin,
        String motivo                  // opcional
) {}
