package br.ifrn.edu.jeferson.ecommerce.domain.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO para requisição de item de pedido")
public class ItemPedidoRequestDTO {
    @Schema(description = "Quantidade do item", example = "1")
    private Integer quantidade;

    @Schema(description = "Valor unitário do item", example = "100.00")
    private String valorUnitario;

    @Schema(description = "Produto do item")
    private ProdutoRequestDTO produto;

    @Schema(description = "Pedido do item")
    private PedidoRequestDTO pedido;
}
