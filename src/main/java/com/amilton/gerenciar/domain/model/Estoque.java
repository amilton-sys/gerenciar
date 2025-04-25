package com.amilton.gerenciar.domain.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.Set;

@Entity
public class Estoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @Column(nullable = false)
    private Integer quantidade;
    @Column(nullable = false)
    private Integer limiteEstoque;
    @ManyToMany
    @JoinTable(
            name = "estoque_produto",
            joinColumns = @JoinColumn(name = "estoque_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id")
    )
    private Set<Produto> produtos;
    @CreationTimestamp
    private OffsetDateTime dataCriacao;
    @UpdateTimestamp
    private OffsetDateTime dataAtualizacao;
}
