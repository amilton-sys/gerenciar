package com.amilton.gerenciar.api.v1.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoResponse {
    private Long id;
    private String nome;
    private String categoria;
}
