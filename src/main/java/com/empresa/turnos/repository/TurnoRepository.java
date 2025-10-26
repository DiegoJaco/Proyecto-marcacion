package com.empresa.turnos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.empresa.turnos.entity.Turno;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {
}
