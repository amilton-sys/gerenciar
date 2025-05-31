package com.amilton.gerenciar.domain.respository;

import com.amilton.gerenciar.domain.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRespository extends JpaRepository<Produto, Long> {
    Optional<Produto> findByNome(String nome);
}
