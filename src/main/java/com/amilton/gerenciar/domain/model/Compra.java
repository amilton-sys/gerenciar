package com.amilton.gerenciar.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @CreationTimestamp
    private OffsetDateTime dataCompra;
    @Column(nullable = false)
    private BigDecimal valorTotal = BigDecimal.ZERO;
    @ManyToMany
    @JoinTable(
            name = "compra_produto",
            joinColumns = @JoinColumn(name = "compra_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id")
    )
    private Set<Produto> itens = new HashSet<>();

    public void calculaValorTotal() {
        setValorTotal(getItens().stream()
                .map(produto -> produto.getValorUnitario().multiply(BigDecimal.valueOf(produto.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add));
    }
}
