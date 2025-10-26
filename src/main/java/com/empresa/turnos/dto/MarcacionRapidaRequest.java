package com.empresa.turnos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record MarcacionRapidaRequest(
        @NotBlank @Pattern(regexp = "\\d{8}", message = "DNI debe tener 8 dígitos")
        String dni,
        @NotBlank(message = "tipo es requerido (ENTRADA o SALIDA)")
        String tipo
) {}