package br.ifrn.edu.jeferson.ecommerce.mapper;

import java.util.List;

import org.mapstruct.*;

import br.ifrn.edu.jeferson.ecommerce.domain.ItemPedido;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ItemPedidoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ItemPedidoResponseDTO;


public interface ItemPedidoMapper {
    
    ItemPedidoResponseDTO toResponseDTO(ItemPedido itemPedido);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "pedido", ignore = true)
    @Mapping(target = "produto", ignore = true)
    ItemPedido toEntity(ItemPedidoRequestDTO dto);

    List<ItemPedidoResponseDTO> toDTOList(List<ItemPedido> itemPedidos);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "pedido", ignore = true)
    @Mapping(target = "produto", ignore = true)
    ItemPedido updateEntityFromDTO(ItemPedidoRequestDTO dto, @MappingTarget ItemPedido itemPedido);
}
