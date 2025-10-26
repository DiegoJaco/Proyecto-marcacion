package com.empresa.turnos.controller;

import com.empresa.turnos.entity.Colaborador;
import com.empresa.turnos.repository.ColaboradorRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/colaboradores")
@Tag(name = "Colaboradores", description = "Gestión de colaboradores")
public class ColaboradorController {

    private final ColaboradorRepository repository;

    public ColaboradorController(ColaboradorRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    @Operation(summary = "Listar colaboradores")
    public List<Colaborador> listar() {
        return repository.findAll();
    }

    @PostMapping
    @Operation(summary = "Crear colaborador")
    public Colaborador crear(@RequestBody Colaborador c) {
        return repository.save(c);
    }
}

