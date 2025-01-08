package br.ifrn.edu.jeferson.ecommerce.domain.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para requisição de produto")
public class ProdutoRequestDTO {
    @Schema(description = "Nome do produto", example = "Notebook")
    private String nome;

    @Schema(description = "Descrição do produto", example = "Notebook Dell")
    private String descricao;

    @Schema(description = "Valor do produto", example = "1000.00")
    private String valor;

    @Schema(description = "Quantidade em estoque do produto", example = "10")
    private Integer quantidadeEstoque;

    @Schema(description = "Categoria do produto")
    private CategoriaRequestDTO categoria;
}
