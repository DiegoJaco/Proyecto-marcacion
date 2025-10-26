package com.empresa.turnos.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.empresa.turnos.entity.Permiso;
import com.empresa.turnos.enums.EstadoPermiso;

public interface PermisoRepository extends JpaRepository<Permiso, Long> {
    List<Permiso> findByColaborador_IdAndFechaInicioLessThanEqualAndFechaFinGreaterThanEqual(
        Long colaboradorId, LocalDate fechaFin, LocalDate fechaInicio);
    List<Permiso> findByEstado(EstadoPermiso estado);
}
