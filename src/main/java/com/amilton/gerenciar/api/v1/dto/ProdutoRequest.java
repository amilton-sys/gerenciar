package com.amilton.gerenciar.api.v1.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoRequest {
    @NotBlank(message = "O nome não pode ser nulo ou vazio")
    @Size(min = 3, max = 30, message = "O nome deve ter entre 3 e 30 caracteres")
    private String nome;
    @NotBlank(message = "A descrição não pode ser nula ou vazia")
    @Size(min = 3, max = 30, message = "A categoria deve ter entre 3 e 30 caracteres")
    private String categoria;
    @Positive(message = "O valor unitário deve ser positivo")
    @Size(min = 1, max = 100, message = "A quantidade deve ser entre 1 e 100")
    private Integer quantidade;
    @NotNull(message = "O valor unitário não pode ser nulo")
    @DecimalMin(value = "0.01", message = "O valor unitário deve ser maior que zero")
    @DecimalMax(value = "9.999,99", message = "O valor unitário deve ser menor que 1.000,00")
    private BigDecimal valorUnitario;
}
