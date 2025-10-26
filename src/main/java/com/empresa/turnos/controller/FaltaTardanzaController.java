package com.empresa.turnos.controller;

import com.empresa.turnos.dto.FaltaTardanzaRequest;
import com.empresa.turnos.entity.Colaborador;
import com.empresa.turnos.entity.FaltaTardanza;
import com.empresa.turnos.repository.ColaboradorRepository;
import com.empresa.turnos.repository.FaltaTardanzaRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/faltas-tardanzas")
public class FaltaTardanzaController {

    private final FaltaTardanzaRepository ftRepo;
    private final ColaboradorRepository colRepo;

    public FaltaTardanzaController(FaltaTardanzaRepository ftRepo, ColaboradorRepository colRepo) {
        this.ftRepo = ftRepo;
        this.colRepo = colRepo;
    }

    @GetMapping
    public List<FaltaTardanza> listar() {
        return ftRepo.findAll();
    }

    @PostMapping
    public ResponseEntity<FaltaTardanza> crear(@Valid @RequestBody FaltaTardanzaRequest req) {
        Colaborador col = colRepo.findById(req.colaboradorId())
                .orElseThrow(() -> new IllegalArgumentException("Colaborador no encontrado"));

        var ft = new FaltaTardanza();
        ft.setColaborador(col);
        ft.setTipo(req.tipo());   // setter String → enum
        ft.setFecha(req.fecha());
        ft.setMinutos(req.minutos());
        ft.setObservacion(req.observacion());

        ft = ftRepo.save(ft);
        return ResponseEntity.created(URI.create("/api/faltas-tardanzas/" + ft.getId())).body(ft);
    }
}

