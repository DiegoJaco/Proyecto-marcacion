package com.empresa.turnos.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empresa.turnos.dto.PermisoRequestDTO;
import com.empresa.turnos.entity.Permiso;
import com.empresa.turnos.enums.EstadoPermiso;
import com.empresa.turnos.exception.NotFoundException;
import com.empresa.turnos.repository.ColaboradorRepository;
import com.empresa.turnos.repository.PermisoRepository;
import com.empresa.turnos.service.PermisoService;

@Service
public class PermisoServiceImpl implements PermisoService {

    private final PermisoRepository permisoRepository;
    private final ColaboradorRepository colaboradorRepository;

    public PermisoServiceImpl(PermisoRepository permisoRepository,
                              ColaboradorRepository colaboradorRepository) {
        this.permisoRepository = permisoRepository;
        this.colaboradorRepository = colaboradorRepository;
    }

    @Override @Transactional
    public Permiso crear(PermisoRequestDTO dto) {
        var col = colaboradorRepository.findById(dto.getColaboradorId()).orElseThrow();
        Permiso p = new Permiso();
        p.setColaborador(col);
        p.setTipo(dto.getTipo());
        p.setFechaInicio(dto.getFechaInicio());
        p.setFechaFin(dto.getFechaFin());
        p.setMotivo(dto.getMotivo());
        p.setEstado(dto.getEstado() != null ? dto.getEstado().name() : EstadoPermiso.PENDIENTE.name());
        return permisoRepository.save(p);
    }

    @Override @Transactional
    public Permiso aprobar(Long id) {
        var p = permisoRepository.findById(id).orElseThrow(() -> new NotFoundException("Permiso no encontrado"));
        p.setEstado(EstadoPermiso.APROBADO.name());
        return permisoRepository.save(p);
    }

    @Override @Transactional
    public Permiso rechazar(Long id) {
        var p = permisoRepository.findById(id).orElseThrow(() -> new NotFoundException("Permiso no encontrado"));
        p.setEstado(EstadoPermiso.RECHAZADO.name());
        return permisoRepository.save(p);
    }

    @Override
    public List<Permiso> listarPorEstado(EstadoPermiso estado) {
        return permisoRepository.findByEstado(estado);
    }
}

