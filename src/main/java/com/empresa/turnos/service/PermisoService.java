package com.empresa.turnos.service;

import java.util.List;
import com.empresa.turnos.dto.PermisoRequestDTO;
import com.empresa.turnos.entity.Permiso;
import com.empresa.turnos.enums.EstadoPermiso;

public interface PermisoService {
    Permiso crear(PermisoRequestDTO dto);
    Permiso aprobar(Long id);
    Permiso rechazar(Long id);
    List<Permiso> listarPorEstado(EstadoPermiso estado);
}
