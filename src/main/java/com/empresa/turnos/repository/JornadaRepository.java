package com.empresa.turnos.repository;

import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.empresa.turnos.entity.Jornada;

public interface JornadaRepository extends JpaRepository<Jornada, Long> {
    Optional<Jornada> findByColaborador_IdAndFecha(Long colaboradorId, LocalDate fecha);
}
