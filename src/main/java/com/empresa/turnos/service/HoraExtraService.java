package com.empresa.turnos.service;

import com.empresa.turnos.dto.HoraExtraDecisionDTO;
import com.empresa.turnos.entity.HoraExtra;

public interface HoraExtraService {
    HoraExtra detectarOAumentar(Long colaboradorId, java.time.LocalDate fecha, int minutos);
    HoraExtra decidir(Long id, HoraExtraDecisionDTO dto);
}
