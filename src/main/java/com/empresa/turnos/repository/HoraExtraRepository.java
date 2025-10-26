package com.empresa.turnos.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.empresa.turnos.entity.HoraExtra;

public interface HoraExtraRepository extends JpaRepository<HoraExtra, Long> {
    List<HoraExtra> findByColaborador_IdAndFecha(Long colaboradorId, LocalDate fecha);
}
