package com.empresa.turnos.service;

import com.empresa.turnos.dto.MarcacionRequestDTO;
import com.empresa.turnos.entity.Marcacion;

public interface MarcacionService {
    Marcacion registrarMarcacion(MarcacionRequestDTO dto);
}
