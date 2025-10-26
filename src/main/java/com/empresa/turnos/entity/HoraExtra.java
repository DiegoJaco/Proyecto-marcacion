package com.empresa.turnos.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter @Setter
@Entity
@Table(name = "hora_extra")
public class HoraExtra {

  public enum Estado { PENDIENTE, APROBADA, RECHAZADA }

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

 
  @ManyToOne(optional = false)
  @JoinColumn(name = "colaborador_id", nullable = false)
  private Colaborador colaborador;

  @Column(nullable = false)
  private LocalDate fecha;

  @Column(name = "minutos_detectados")
  private Integer minutosDetectados;

  @Column(name = "minutos_aprobados")
  private Integer minutosAprobados;

  @Enumerated(EnumType.STRING) @Column(nullable = false)
  private Estado estado = Estado.PENDIENTE;

  
  @ManyToOne
  @JoinColumn(name = "jornada_id")
  private Jornada jornada;

  
  public void setEstado(String value) {
    this.estado = value == null ? null : Estado.valueOf(value.toUpperCase());
  }
}



