package com.empresa.turnos.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.empresa.turnos.dto.PermisoRequestDTO;
import com.empresa.turnos.entity.Permiso;
import com.empresa.turnos.enums.EstadoPermiso;
import com.empresa.turnos.service.PermisoService;

@RestController
@RequestMapping("/api/permisos")
public class PermisoController {

    private final PermisoService permisoService;

    public PermisoController(PermisoService permisoService) {
        this.permisoService = permisoService;
    }

    @PostMapping
    public ResponseEntity<Permiso> crear(@RequestBody PermisoRequestDTO dto) {
        return ResponseEntity.ok(permisoService.crear(dto));
    }

    @PostMapping("/{id}/aprobar")
    public ResponseEntity<Permiso> aprobar(@PathVariable Long id) {
        return ResponseEntity.ok(permisoService.aprobar(id));
    }

    @PostMapping("/{id}/rechazar")
    public ResponseEntity<Permiso> rechazar(@PathVariable Long id) {
        return ResponseEntity.ok(permisoService.rechazar(id));
    }

    @GetMapping
    public ResponseEntity<List<Permiso>> listarPorEstado(@RequestParam(required = false) EstadoPermiso estado) {
        if (estado == null) estado = EstadoPermiso.PENDIENTE;
        return ResponseEntity.ok(permisoService.listarPorEstado(estado));
    }
}


