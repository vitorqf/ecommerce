package br.ifrn.edu.jeferson.ecommerce.domain.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO para resposta de pedido")
public class PedidoResponseDTO {
    @Schema(description = "ID do pedido", example = "1")
    private Long id;

    @Schema(description = "Valor total do pedido", example = "100.00")
    private String valorTotal;

    @Schema(description = "Status do pedido", example = "AGUARDANDO")
    private String statusPedido;

    @Schema(description = "Cliente do pedido")
    private ClienteResponseDTO cliente;

    @Schema(description = "Itens do pedido")
    private ItemPedidoResponseDTO itemPedido;
}
