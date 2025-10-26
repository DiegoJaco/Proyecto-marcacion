package com.empresa.turnos.service.impl;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empresa.turnos.dto.HoraExtraDecisionDTO;
import com.empresa.turnos.entity.HoraExtra;
import com.empresa.turnos.enums.EstadoHoraExtra;
import com.empresa.turnos.exception.NotFoundException;
import com.empresa.turnos.repository.ColaboradorRepository;
import com.empresa.turnos.repository.HoraExtraRepository;
import com.empresa.turnos.service.HoraExtraService;

@Service
public class HoraExtraServiceImpl implements HoraExtraService {

    private final HoraExtraRepository horaExtraRepository;
    private final ColaboradorRepository colaboradorRepository;

    public HoraExtraServiceImpl(HoraExtraRepository horaExtraRepository,
                                ColaboradorRepository colaboradorRepository) {
        this.horaExtraRepository = horaExtraRepository;
        this.colaboradorRepository = colaboradorRepository;
    }

    @Override
    @Transactional
    public HoraExtra detectarOAumentar(Long colaboradorId, LocalDate fecha, int minutos) {
        var list = horaExtraRepository.findByColaborador_IdAndFecha(colaboradorId, fecha);
        HoraExtra he = list.isEmpty() ? new HoraExtra() : list.get(0);
        if (he.getId() == null) {
            he.setColaborador(colaboradorRepository.findById(colaboradorId).orElseThrow());
            he.setFecha(fecha);
            he.setMinutosDetectados(minutos);
            he.setMinutosAprobados(0);
            he.setEstado(EstadoHoraExtra.PENDIENTE.name());
        } else {
            he.setMinutosDetectados(he.getMinutosDetectados() + minutos);
        }
        return horaExtraRepository.save(he);
    }

    @Override
    @Transactional
    public HoraExtra decidir(Long id, HoraExtraDecisionDTO dto) {
        var he = horaExtraRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Hora extra no encontrada"));
        if (dto.getEstado() == EstadoHoraExtra.APROBADO) {
            he.setEstado(EstadoHoraExtra.APROBADO.name());
            he.setMinutosAprobados(dto.getMinutosAprobados() != null ? dto.getMinutosAprobados() : he.getMinutosDetectados());
        } else {
            he.setEstado(EstadoHoraExtra.RECHAZADO.name());
            he.setMinutosAprobados(0);
        }
        return horaExtraRepository.save(he);
    }
}
