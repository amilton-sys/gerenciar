package com.amilton.gerenciar.domain.model;

import jakarta.persistence.*;


import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
public class Plano {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private BigDecimal valor;
    private OffsetDateTime dataInicio;
    private OffsetDateTime dataFim;
}
