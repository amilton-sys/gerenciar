package com.amilton.gerenciar.api.v1.assembler;

import com.amilton.gerenciar.api.v1.dto.ProdutoRequest;
import com.amilton.gerenciar.domain.model.Produto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProdutoRequestDisassembler {
    private final ModelMapper modelMapper;

    public Produto toDomainObject(ProdutoRequest produtoRequest) {
        return modelMapper.map(produtoRequest, Produto.class);
    }
}
