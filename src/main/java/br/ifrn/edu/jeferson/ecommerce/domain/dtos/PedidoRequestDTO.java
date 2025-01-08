package br.ifrn.edu.jeferson.ecommerce.domain.dtos;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO para requisição de pedido")
public class PedidoRequestDTO {
    @Schema(description = "Valor total do pedido", example = "100.00")
    private String valorTotal;

    @Schema(description = "Status do pedido", example = "AGUARDANDO")
    private String statusPedido;

    @Schema(description = "Cliente do pedido")
    private ClienteRequestDTO cliente;

    @Schema(description = "Itens do pedido")
    private List<ItemPedidoRequestDTO> itensPedido;
}
