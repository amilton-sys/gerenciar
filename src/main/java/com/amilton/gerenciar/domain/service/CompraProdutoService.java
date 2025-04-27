package com.amilton.gerenciar.domain.service;

import com.amilton.gerenciar.domain.model.Compra;
import com.amilton.gerenciar.domain.model.HistoricoPreco;
import com.amilton.gerenciar.domain.model.Produto;
import com.amilton.gerenciar.domain.respository.CompraRespository;
import com.amilton.gerenciar.domain.respository.HistoricoPrecoRepository;
import com.amilton.gerenciar.domain.respository.ProdutoRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CompraProdutoService {
    private final CompraService compraService;
    private final CompraRespository compraRespository;
    private final ProdutoRespository produtoRespository;
    private final HistoricoPrecoRepository historicoPrecoRepository;

    public Set<Produto> listarProdutos(Long compraId) {
        Compra compra = compraService.buscar(compraId);
        return compra.getItens();
    }

    @Transactional
    public void adicionarProduto(Long compraId, Long produtoId) {
        Compra compra = compraService.buscar(compraId);
        Produto produto = produtoRespository.findById(produtoId).orElseThrow(() -> new RuntimeException("Produto de id: %s nao encontrado.".formatted(produtoId)));

        HistoricoPreco historicoPrecoExistente = historicoPrecoRepository.findByProdutoId(produtoId).orElse(null);

        if (historicoPrecoExistente != null) {
            throw new RuntimeException("Produto de id: %s já possui um histórico de preço.".formatted(produtoId));
        }

        HistoricoPreco historicoPreco = new HistoricoPreco();
        historicoPreco.setProduto(produto);
        historicoPreco.setPrecoAtual(produto.getValorUnitario());
        historicoPrecoRepository.save(historicoPreco);

        compra.getItens().add(produto);
        compraRespository.save(compra);
    }

    @Transactional
    public void atualizarProduto(Long compraId, Long produtoId) {
        Compra compra = compraService.buscar(compraId);
        Produto produto = produtoRespository.findById(produtoId).orElseThrow(() -> new RuntimeException("Produto de id: %s nao encontrado.".formatted(produtoId)));

        HistoricoPreco historicoPreco = historicoPrecoRepository.findByProdutoId(produtoId).orElseThrow(() -> new RuntimeException("Historico de preco do produto de id: %s nao encontrado.".formatted(produtoId)));
        historicoPreco.setPrecoAnterior(historicoPreco.getPrecoAtual());
        historicoPreco.setPrecoAtual(produto.getValorUnitario());

        compra.getItens().add(produto);
        compraRespository.save(compra);
    }

    @Transactional
    public void removerProduto(Long compraId, Long produtoId) {
        Compra compra = compraService.buscar(compraId);
        Produto produto = produtoRespository.findById(produtoId).orElseThrow(() -> new RuntimeException("Produto de id: %s nao encontrado.".formatted(produtoId)));

        HistoricoPreco historicoPreco = historicoPrecoRepository.findByProdutoId(produtoId).orElseThrow(() -> new RuntimeException("Historico de preco do produto de id: %s nao encontrado.".formatted(produtoId)));
        historicoPrecoRepository.delete(historicoPreco);

        compra.getItens().remove(produto);
        compraRespository.save(compra);
    }

}
