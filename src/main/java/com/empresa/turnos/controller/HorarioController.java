package com.empresa.turnos.controller;

import com.empresa.turnos.entity.Colaborador;
import com.empresa.turnos.entity.Horario;
import com.empresa.turnos.entity.Turno;
import com.empresa.turnos.repository.ColaboradorRepository;
import com.empresa.turnos.repository.HorarioRepository;
import com.empresa.turnos.repository.TurnoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Horarios", description = "Programación de horarios")
@RestController
@RequestMapping("/api/horarios")
public class HorarioController {

    private final HorarioRepository horarioRepository;
    private final ColaboradorRepository colaboradorRepository;
    private final TurnoRepository turnoRepository;

    public HorarioController(HorarioRepository horarioRepository,
                             ColaboradorRepository colaboradorRepository,
                             TurnoRepository turnoRepository) {
        this.horarioRepository = horarioRepository;
        this.colaboradorRepository = colaboradorRepository;
        this.turnoRepository = turnoRepository;
    }

    @Operation(summary = "Listar horarios")
    @GetMapping
    public List<Horario> listar() {
        return horarioRepository.findAll();
    }

    record NuevoHorarioDTO(@NotNull Long colaboradorId,
                           @NotNull Long turnoId,
                           @NotBlank String diaSemana) {}

    @Operation(summary = "Crear horario (colaborador + turno + día)")
    @PostMapping
    public ResponseEntity<Horario> crear(@RequestBody NuevoHorarioDTO dto) {
        Colaborador col = colaboradorRepository.findById(dto.colaboradorId())
                .orElseThrow(() -> new IllegalArgumentException("Colaborador no encontrado"));
        Turno turno = turnoRepository.findById(dto.turnoId())
                .orElseThrow(() -> new IllegalArgumentException("Turno no encontrado"));

        Horario h = new Horario();
        h.setColaborador(col);
        h.setTurno(turno);
        h.setDiaSemana(dto.diaSemana());

        return ResponseEntity.ok(horarioRepository.save(h));
    }
}

