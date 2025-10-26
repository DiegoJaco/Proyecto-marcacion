package com.empresa.turnos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.empresa.turnos.dto.HoraExtraDecisionDTO;
import com.empresa.turnos.entity.HoraExtra;
import com.empresa.turnos.service.HoraExtraService;

@RestController
@RequestMapping("/api/horas-extra")
public class HoraExtraController {

    private final HoraExtraService horaExtraService;

    public HoraExtraController(HoraExtraService horaExtraService) {
        this.horaExtraService = horaExtraService;
    }

    @PostMapping("/{id}/decidir")
    public ResponseEntity<HoraExtra> decidir(@PathVariable Long id, @RequestBody HoraExtraDecisionDTO dto) {
        return ResponseEntity.ok(horaExtraService.decidir(id, dto));
    }
}

