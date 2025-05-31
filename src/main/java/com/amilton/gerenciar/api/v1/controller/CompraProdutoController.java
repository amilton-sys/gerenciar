package com.amilton.gerenciar.api.v1.controller;

import com.amilton.gerenciar.domain.model.Produto;
import com.amilton.gerenciar.domain.service.CompraProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/v1/compras/{compraId}/produtos")
@RequiredArgsConstructor
public class CompraProdutoController {
    private final CompraProdutoService compraProdutoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Set<Produto> listar(@PathVariable Long compraId) {
      return compraProdutoService.listarProdutos(compraId);
    }

    @PostMapping("/{produtoId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionar(@PathVariable Long compraId, @PathVariable Long produtoId) {
        compraProdutoService.adicionarProduto(compraId, produtoId);
    }

    @PutMapping("/{produtoId}")
    @ResponseStatus(HttpStatus.OK)
    public void atualizar(@PathVariable Long compraId, @PathVariable Long produtoId) {
        compraProdutoService.atualizarProduto(compraId, produtoId);
    }

    @DeleteMapping("/{produtoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long compraId, @PathVariable Long produtoId) {
        compraProdutoService.removerProduto(compraId, produtoId);
    }

}
