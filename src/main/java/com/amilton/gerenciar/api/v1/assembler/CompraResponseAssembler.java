package com.amilton.gerenciar.api.v1.assembler;

import com.amilton.gerenciar.api.v1.dto.CompraResponse;
import com.amilton.gerenciar.domain.model.Compra;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompraResponseAssembler {
    private final ModelMapper modelMapper;

    public CompraResponse toModel(Compra compra) {
        return modelMapper.map(compra, CompraResponse.class);
    }
}
