package com.amilton.gerenciar.api.v1.assembler;

import com.amilton.gerenciar.api.v1.dto.ProdutoResponse;
import com.amilton.gerenciar.domain.model.Produto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProdutoResponseAssembler {
    private final ModelMapper modelMapper;

    public ProdutoResponse toModel(Produto produto) {
        return modelMapper.map(produto, ProdutoResponse.class);
    }

    public Set<ProdutoResponse> toCollectionModel(Set<Produto> produtos) {
        return produtos.stream()
                .map(this::toModel)
                .collect(Collectors.toSet());
    }
}
