package com.empresa.turnos.repository;

import com.empresa.turnos.entity.Marcacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MarcacionRepository extends JpaRepository<Marcacion, Long> {
    Optional<Marcacion> findTopByColaborador_IdOrderByFechaHoraDesc(Long colaboradorId);
    List<Marcacion> findByColaborador_IdAndFechaHoraBetweenOrderByFechaHoraAsc(Long colaboradorId,
                                                                               LocalDateTime desde,
                                                                               LocalDateTime hasta);
}