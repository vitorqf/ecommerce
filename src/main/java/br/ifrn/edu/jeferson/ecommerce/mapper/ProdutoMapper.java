package br.ifrn.edu.jeferson.ecommerce.mapper;

import org.mapstruct.*;

import br.ifrn.edu.jeferson.ecommerce.domain.Produto;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ProdutoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ProdutoResponseDTO;

public interface ProdutoMapper {
    
    ProdutoResponseDTO toResponseDTO(Produto produto);

    @Mapping(target = "id", ignore = true)
    Produto toEntity(ProdutoRequestDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    Produto updateEntityFromDTO(ProdutoRequestDTO dto, @MappingTarget Produto produto);
}
