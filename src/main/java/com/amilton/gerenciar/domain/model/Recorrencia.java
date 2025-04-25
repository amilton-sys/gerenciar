package com.amilton.gerenciar.domain.model;

import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
public class Recorrencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String tipo;
    @Column(nullable = false)
    private Integer intervalo;
    @Column(nullable = false)
    private OffsetDateTime dataInicio;
    private OffsetDateTime dataFim;
    @ManyToOne
    @JoinColumn(name = "despesa_id")
    public Despesa despesa;
}


