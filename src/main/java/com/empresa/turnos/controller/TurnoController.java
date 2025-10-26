package com.empresa.turnos.controller;

import com.empresa.turnos.entity.Turno;
import com.empresa.turnos.repository.TurnoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Turnos", description = "Gestión de turnos")
@RestController
@RequestMapping("/api/turnos")
public class TurnoController {

    private final TurnoRepository turnoRepository;

    public TurnoController(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    @Operation(summary = "Listar turnos")
    @GetMapping
    public List<Turno> listar() {
        return turnoRepository.findAll();
    }

    @Operation(summary = "Crear turno")
    @PostMapping
    public ResponseEntity<Turno> crear(@Valid @RequestBody Turno turno) {
        return ResponseEntity.ok(turnoRepository.save(turno));
    }
}
