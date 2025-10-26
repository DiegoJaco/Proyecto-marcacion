package com.empresa.turnos.service.impl;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empresa.turnos.entity.*;
import com.empresa.turnos.enums.EstadoJornada;
import com.empresa.turnos.repository.*;
import com.empresa.turnos.service.JornadaService;

@Service
public class JornadaServiceImpl implements JornadaService {

    private final JornadaRepository jornadaRepository;
    private final TurnoRepository turnoRepository;
    private final ColaboradorRepository colaboradorRepository;

    public JornadaServiceImpl(JornadaRepository jornadaRepository,
                              TurnoRepository turnoRepository,
                              ColaboradorRepository colaboradorRepository) {
        this.jornadaRepository = jornadaRepository;
        this.turnoRepository = turnoRepository;
        this.colaboradorRepository = colaboradorRepository;
    }

    @Override
    @Transactional
    public Jornada obtenerOCrear(Long colaboradorId, LocalDate fecha, Long turnoIdSugerido) {
        return jornadaRepository.findByColaborador_IdAndFecha(colaboradorId, fecha)
            .orElseGet(() -> {
                Jornada j = new Jornada();
                j.setColaborador(colaboradorRepository.findById(colaboradorId).orElseThrow());
                j.setFecha(fecha);
                if (turnoIdSugerido != null)
                    j.setTurno(turnoRepository.findById(turnoIdSugerido).orElse(null));
                j.setEstado(EstadoJornada.PLANIFICADA.name());
                return jornadaRepository.save(j);
            });
    }

    @Override
    @Transactional
    public Jornada cerrarSiCorresponde(Long colaboradorId, LocalDate fecha) {
        var j = jornadaRepository.findByColaborador_IdAndFecha(colaboradorId, fecha).orElseThrow();
        if (j.getHoraEntrada() != null && j.getHoraSalida() != null) {
            j.setEstado(EstadoJornada.CERRADA.name());
        } else {
            j.setEstado(EstadoJornada.EN_PROCESO.name());
        }
        return jornadaRepository.save(j);
    }
}
