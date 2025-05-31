package com.amilton.gerenciar.domain.service;

import com.amilton.gerenciar.domain.model.Produto;
import com.amilton.gerenciar.domain.respository.ProdutoRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRespository produtoRespository;

    @Transactional
    public Produto criar(Produto produto) {
        if (produtoRespository.findByNome(produto.getNome()).isPresent()) {
            throw new RuntimeException("Produto de nome: %s já existe".formatted(produto.getNome()));
        }
        return produtoRespository.save(produto);
    }

    @Transactional(readOnly = true)
    public Set<Produto> listar() {
        return new HashSet<>(produtoRespository.findAll());
    }
}
