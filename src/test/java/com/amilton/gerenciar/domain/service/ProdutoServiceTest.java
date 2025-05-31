package com.amilton.gerenciar.domain.service;

import com.amilton.gerenciar.domain.model.Produto;
import com.amilton.gerenciar.domain.respository.ProdutoRespository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {
    @Mock
    private ProdutoRespository produtoRespository;
    @InjectMocks
    private ProdutoService produtoService;

    @BeforeEach
    void setUp() {
        produtoService = new ProdutoService(produtoRespository);
    }

    @Test
    void criar() {
        Produto produto = new Produto();
        produto.setNome("Produto Teste");
        produto.setValorUnitario(BigDecimal.TEN);
        produto.setId(1L);
        produto.setDataCriacao(OffsetDateTime.now());
        produto.setDataAtualizacao(OffsetDateTime.now());

        when(produtoRespository.findByNome(produto.getNome())).thenReturn(Optional.empty());
        when(produtoRespository.save(produto)).thenReturn(produto);

        produtoService.criar(produto);

        verify(produtoRespository, times(1)).findByNome(produto.getNome());
        verify(produtoRespository, times(1)).save(produto);
    }

    @Test
    void criarProdutoJaExistente() {
        Produto produto = new Produto();
        produto.setNome("Produto Teste");
        produto.setValorUnitario(BigDecimal.TEN);
        produto.setId(1L);
        produto.setDataCriacao(OffsetDateTime.now());
        produto.setDataAtualizacao(OffsetDateTime.now());

        when(produtoRespository.findByNome(produto.getNome())).thenReturn(Optional.of(produto));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            produtoService.criar(produto);
        });

        String expectedMessage = "Produto de nome: %s já existe".formatted(produto.getNome());
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}