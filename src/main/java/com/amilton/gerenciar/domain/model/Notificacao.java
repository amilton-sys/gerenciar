package com.amilton.gerenciar.domain.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;

@Entity
public class Notificacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @Column(nullable = false)
    private String tipo;
    @Column(nullable = false)
    private String descricao;
    @CreationTimestamp
    private OffsetDateTime dataCriacao;
    @UpdateTimestamp
    private OffsetDateTime dataAtualizacao;
}
