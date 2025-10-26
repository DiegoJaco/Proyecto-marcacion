package com.empresa.turnos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import com.empresa.turnos.entity.Horario;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {
    Optional<Horario> findFirstByColaborador_IdAndDiaSemana(Long colaboradorId, String diaSemana);
}