package com.empresa.turnos.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record HoraExtraRequest(
        @NotNull Long colaboradorId,
        @NotNull LocalDate fecha,
        Integer minutosDetectados,
        Integer minutosAprobados,
        String estado                  // PENDIENTE, APROBADA, RECHAZADA
) {}
