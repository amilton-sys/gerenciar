package com.amilton.gerenciar.domain.respository;

import com.amilton.gerenciar.domain.model.HistoricoPreco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HistoricoPrecoRepository extends JpaRepository<HistoricoPreco,Long> {
    Optional<HistoricoPreco> findByProdutoId(Long produtoId);
}
