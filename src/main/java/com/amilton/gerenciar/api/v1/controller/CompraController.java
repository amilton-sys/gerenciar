package com.amilton.gerenciar.api.v1.controller;

import com.amilton.gerenciar.api.v1.assembler.CompraResponseAssembler;
import com.amilton.gerenciar.api.v1.dto.CompraResponse;
import com.amilton.gerenciar.api.v1.helpers.ResourceUriHelper;
import com.amilton.gerenciar.domain.model.Compra;
import com.amilton.gerenciar.domain.model.Usuario;
import com.amilton.gerenciar.domain.service.CompraService;
import com.amilton.gerenciar.domain.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("api/v1/compras")
@RequiredArgsConstructor
public class CompraController {
    private final CompraService compraService;
    private final UsuarioService usuarioService;
    private final CompraResponseAssembler compraResponseAssembler;

    @GetMapping("/{compraId}")
    public CompraResponse buscar(@PathVariable Long compraId) {
        Compra compra = compraService.buscar(compraId);
        compra.calculaValorTotal();
        return compraResponseAssembler.toModel(compra);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> criar() {
        // TODO remover após implementação do login
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        Compra compra = compraService.criar(usuario.getId());

        URI uri = ResourceUriHelper.buildUri(compra.getId());

        return ResponseEntity.created(uri).body(compraResponseAssembler.toModel(compra));
    }
}
