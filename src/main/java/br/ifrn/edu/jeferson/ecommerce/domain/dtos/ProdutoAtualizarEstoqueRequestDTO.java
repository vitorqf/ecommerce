package br.ifrn.edu.jeferson.ecommerce.domain.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO para atualização de status de um pedido")
public class ProdutoAtualizarEstoqueRequestDTO {
    @Schema(description = "Quantidade no estoque", example = "10")
    @NotNull(message = "Quantidade é obrigatório")
    @Min(value = 0, message = "Quantidade não pode ser negativo")
    private Integer quantidade;
}
