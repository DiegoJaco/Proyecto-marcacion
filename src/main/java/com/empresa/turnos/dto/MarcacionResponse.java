package com.empresa.turnos.dto;

import java.time.LocalDateTime;

public record MarcacionResponse(
        Long id,
        Long colaboradorId,
        LocalDateTime fechaHora,
        String tipo,
        String origen
) {}