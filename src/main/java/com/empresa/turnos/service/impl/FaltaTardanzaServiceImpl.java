package com.empresa.turnos.service.impl;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empresa.turnos.entity.Colaborador;
import com.empresa.turnos.entity.FaltaTardanza;
import com.empresa.turnos.enums.TipoFaltaTardanza;
import com.empresa.turnos.repository.ColaboradorRepository;
import com.empresa.turnos.repository.FaltaTardanzaRepository;
import com.empresa.turnos.service.FaltaTardanzaService;

@Service
public class FaltaTardanzaServiceImpl implements FaltaTardanzaService {

    private final FaltaTardanzaRepository ftRepository;
    private final ColaboradorRepository colaboradorRepository;

    public FaltaTardanzaServiceImpl(FaltaTardanzaRepository ftRepository,
                                    ColaboradorRepository colaboradorRepository) {
        this.ftRepository = ftRepository;
        this.colaboradorRepository = colaboradorRepository;
    }

    @Override
    @Transactional
    public FaltaTardanza registrar(Long colaboradorId, LocalDate fecha, TipoFaltaTardanza tipo, Integer minutos, String observacion) {
        Colaborador col = colaboradorRepository.findById(colaboradorId).orElseThrow();
        FaltaTardanza ft = new FaltaTardanza();
        ft.setColaborador(col);
        ft.setFecha(fecha);
        ft.setTipo(tipo.name());
        ft.setMinutos(minutos != null ? minutos : 0);
        ft.setObservacion(observacion);
        return ftRepository.save(ft);
    }
}
