package com.empresa.turnos.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.empresa.turnos.entity.FaltaTardanza;

public interface FaltaTardanzaRepository extends JpaRepository<FaltaTardanza, Long> {
    List<FaltaTardanza> findByColaborador_IdAndFecha(Long colaboradorId, LocalDate fecha);
}
