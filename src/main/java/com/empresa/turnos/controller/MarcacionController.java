package com.empresa.turnos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.empresa.turnos.dto.MarcacionRequestDTO;
import com.empresa.turnos.entity.Marcacion;
import com.empresa.turnos.service.MarcacionService;

@RestController
@RequestMapping("/api/marcaciones")
public class MarcacionController {

    private final MarcacionService marcacionService;

    public MarcacionController(MarcacionService marcacionService) {
        this.marcacionService = marcacionService;
    }

    @PostMapping
    public ResponseEntity<Marcacion> registrar(@RequestBody MarcacionRequestDTO dto) {
        return ResponseEntity.ok(marcacionService.registrarMarcacion(dto));
    }
}





