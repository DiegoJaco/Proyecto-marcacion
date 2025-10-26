package com.empresa.turnos.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empresa.turnos.dto.MarcacionRequestDTO;
import com.empresa.turnos.entity.*;
import com.empresa.turnos.enums.TipoMarcacion;
import com.empresa.turnos.exception.BusinessException;
import com.empresa.turnos.exception.NotFoundException;
import com.empresa.turnos.repository.*;
import com.empresa.turnos.service.*;

@Service
public class MarcacionServiceImpl implements com.empresa.turnos.service.MarcacionService {

    private final ColaboradorRepository colaboradorRepository;
    private final TurnoRepository turnoRepository;
    private final HorarioRepository horarioRepository;
    private final MarcacionRepository marcacionRepository;
    private final JornadaService jornadaService;
    private final FaltaTardanzaService faltaTardanzaService;
    private final HoraExtraService horaExtraService;
    private final PermisoRepository permisoRepository;

    public MarcacionServiceImpl(
            ColaboradorRepository colaboradorRepository,
            TurnoRepository turnoRepository,
            HorarioRepository horarioRepository,
            MarcacionRepository marcacionRepository,
            JornadaService jornadaService,
            FaltaTardanzaService faltaTardanzaService,
            HoraExtraService horaExtraService,
            PermisoRepository permisoRepository) {
        this.colaboradorRepository = colaboradorRepository;
        this.turnoRepository = turnoRepository;
        this.horarioRepository = horarioRepository;
        this.marcacionRepository = marcacionRepository;
        this.jornadaService = jornadaService;
        this.faltaTardanzaService = faltaTardanzaService;
        this.horaExtraService = horaExtraService;
        this.permisoRepository = permisoRepository;
    }

    @Override
    @Transactional
    public Marcacion registrarMarcacion(MarcacionRequestDTO dto) {
        var col = colaboradorRepository.findById(dto.getColaboradorId())
            .orElseThrow(() -> new NotFoundException("Colaborador no encontrado"));

        // Si tiene permiso en la fecha, permitimos marcación pero NO calculamos falta/tardanza
        var fecha = dto.getFechaHora().toLocalDate();
        var permisos = permisoRepository
            .findByColaborador_IdAndFechaInicioLessThanEqualAndFechaFinGreaterThanEqual(col.getId(), fecha, fecha);
        boolean enPermiso = permisos != null && !permisos.isEmpty();

        // Obtener horario/turno segun día de semana
        String dia = dto.getFechaHora().getDayOfWeek().name().substring(0,3); // MON,TUE... o usa tu convención
        var horarioOpt = horarioRepository.findFirstByColaborador_IdAndDiaSemana(col.getId(), dia);
        if (horarioOpt.isEmpty()) {
            if (!enPermiso) throw new BusinessException("No existe horario para el día " + dia);
        }
        Long turnoId = horarioOpt.map(Horario::getTurno).map(Turno::getId).orElse(null);

        // Asegurar jornada
        var jornada = jornadaService.obtenerOCrear(col.getId(), fecha, turnoId);

        // Guardar marcación
        Marcacion m = new Marcacion();
        m.setColaborador(col);
        m.setFechaHora(dto.getFechaHora());
        m.setOrigen(dto.getOrigen().name());
        m.setTipo(dto.getTipo().name());
        m = marcacionRepository.save(m);

        // Reglas de negocio básicas:
        var turno = (turnoId != null) ? turnoRepository.findById(turnoId).orElse(null) : null;
        if (dto.getTipo() == TipoMarcacion.ENTRADA) {
            jornada.setHoraEntrada(dto.getFechaHora().toLocalTime());
            // Calcular tardanza
            if (!enPermiso && turno != null) {
                LocalTime sup = turno.getHoraInicio();
                int tol = turno.getMinutosTolerancia() != null ? turno.getMinutosTolerancia() : 0;
                long diff = ChronoUnit.MINUTES.between(sup, jornada.getHoraEntrada());
                int tardanza = (int)Math.max(0, diff - tol);
                jornada.setMinutosTardanza(tardanza);
                if (tardanza > 0) {
                    faltaTardanzaService.registrar(col.getId(), fecha,
                        com.empresa.turnos.enums.TipoFaltaTardanza.TARDANZA, tardanza, "Tardanza por entrada tardía");
                }
            }
        } else { // SALIDA
            jornada.setHoraSalida(dto.getFechaHora().toLocalTime());
            // Calcular horas extra simples (si sale después de hora_fin)
            if (!enPermiso && turno != null && jornada.getHoraSalida() != null) {
                long extra = ChronoUnit.MINUTES.between(turno.getHoraFin(), jornada.getHoraSalida());
                int minutosExtra = (int)Math.max(0, extra);
                jornada.setMinutosExtra(minutosExtra);
                if (minutosExtra > 0) {
                    horaExtraService.detectarOAumentar(col.getId(), fecha, minutosExtra);
                }
            }
            // Cerrar jornada
            jornadaService.cerrarSiCorresponde(col.getId(), fecha);
        }

        return m;
    }
}
