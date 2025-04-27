package com.amilton.gerenciar.domain.service;

import com.amilton.gerenciar.domain.model.Compra;
import com.amilton.gerenciar.domain.model.Usuario;
import com.amilton.gerenciar.domain.respository.CompraRespository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CompraService {
    private final CompraRespository compraRespository;
    private final UsuarioService usuarioService;

    @Transactional
    public Compra criar(final Long usuarioId) {
        Usuario usuario = usuarioService.buscar(usuarioId);
        Optional<Compra> compraExistente = compraRespository.findByUsuarioId(usuarioId);

        if (compraExistente.isPresent()) {
            throw new RuntimeException("Usuario de id: %s já possui uma compra".formatted(usuarioId));
        }
        Compra compra = new Compra();
        compra.setUsuario(usuario);
        return this.compraRespository.save(compra);
    }

    public Compra buscar(Long id) {
        return compraRespository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compra de id: %s não encontrada".formatted(id)));
    }

    public Compra buscarPor(Long usuarioId) {
        return compraRespository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new RuntimeException("Compra do usuario de id: %s não encontrada".formatted(usuarioId)));
    }
}
