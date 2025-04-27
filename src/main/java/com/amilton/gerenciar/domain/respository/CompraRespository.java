package com.amilton.gerenciar.domain.respository;

import com.amilton.gerenciar.domain.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompraRespository extends JpaRepository<Compra, Long> {
    Optional<Compra> findByUsuarioId(Long usuarioId);
}
