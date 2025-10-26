package com.empresa.turnos.repository;

import com.empresa.turnos.entity.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {
    Optional<Colaborador> findByDocumento(String documento);
}