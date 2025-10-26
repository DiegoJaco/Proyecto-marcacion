package com.empresa.turnos.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "falta_tardanza")
public class FaltaTardanza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Colaborador colaborador;
    
    @ManyToOne
    @JoinColumn(name = "jornada_id")
    private Jornada jornada;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Tipo tipo;

    @Column(nullable = false)
    private LocalDate fecha;

   
    private Integer minutos;

   
    @Column(length = 255)
    private String observacion;

    public enum Tipo { FALTA, TARDANZA, SALIDA_ANTICIPADA }

    
    public void setTipo(String tipo) {
        this.tipo = Tipo.valueOf(tipo.toUpperCase());
    }
}

