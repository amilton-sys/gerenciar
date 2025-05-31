package com.amilton.gerenciar.api.v1.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class CompraResponse{
    private Long id;
    private OffsetDateTime dataCompra;
    private BigDecimal valorTotal;
}
