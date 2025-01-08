package br.ifrn.edu.jeferson.ecommerce.mapper;

import br.ifrn.edu.jeferson.ecommerce.domain.Pedido;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.PedidoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.PedidoResponseDTO;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PedidoMapper {
    
    PedidoResponseDTO toResponseDTO(Pedido pedido);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "itens", ignore = true)
    @Mapping(target = "cliente", ignore = true)
    @Mapping(target = "dataPedido", ignore = true)
    Pedido toEntity(PedidoRequestDTO dto);

    List<PedidoResponseDTO> toDTOList(List<Pedido> pedidos);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "itens", ignore = true)
    @Mapping(target = "cliente", ignore = true)
    @Mapping(target = "dataPedido", ignore = true)
    Pedido updateEntityFromDTO(PedidoRequestDTO dto, @MappingTarget Pedido pedido);
}
