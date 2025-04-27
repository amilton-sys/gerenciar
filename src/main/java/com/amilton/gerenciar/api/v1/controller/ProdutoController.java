package com.amilton.gerenciar.api.v1.controller;

import com.amilton.gerenciar.api.v1.assembler.ProdutoRequestDisassembler;
import com.amilton.gerenciar.api.v1.assembler.ProdutoResponseAssembler;
import com.amilton.gerenciar.api.v1.dto.ProdutoRequest;
import com.amilton.gerenciar.api.v1.dto.ProdutoResponse;
import com.amilton.gerenciar.api.v1.helpers.ResourceUriHelper;
import com.amilton.gerenciar.domain.model.Produto;
import com.amilton.gerenciar.domain.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Set;

@RestController
@RequestMapping("api/v1/produtos")
@RequiredArgsConstructor
public class ProdutoController {
    private final ProdutoService produtoService;
    private final ProdutoRequestDisassembler produtoRequestDisassembler;
    private final ProdutoResponseAssembler produtoResponseAssembler;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Set<ProdutoResponse> listar() {
        return produtoResponseAssembler.toCollectionModel(produtoService.listar());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> criar(@RequestBody @Valid ProdutoRequest produtoRequest) {
        Produto produto = produtoRequestDisassembler.toDomainObject(produtoRequest);
        Produto produtoSalvo = produtoService.criar(produto);

        URI uri = ResourceUriHelper.buildUri(produtoSalvo.getId());

        return ResponseEntity.created(uri).body(produtoResponseAssembler.toModel(produtoSalvo));
    }
}
