package br.ifrn.edu.jeferson.ecommerce.domain.dtos;

import java.util.List;

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
    private String total;

    @Schema(description = "Status do pedido", example = "AGUARDANDO")
    private String status;

    @Schema(description = "Itens do pedido")
    private List<ItemPedidoResponseDTO> itens;
}
