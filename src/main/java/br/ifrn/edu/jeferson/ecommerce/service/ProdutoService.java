package br.ifrn.edu.jeferson.ecommerce.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.ifrn.edu.jeferson.ecommerce.domain.Categoria;
import br.ifrn.edu.jeferson.ecommerce.domain.Produto;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ProdutoAtualizarEstoqueRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ProdutoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ProdutoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.exception.ResourceNotFoundException;
import br.ifrn.edu.jeferson.ecommerce.mapper.ProdutoMapper;
import br.ifrn.edu.jeferson.ecommerce.repository.CategoriaRepository;
import br.ifrn.edu.jeferson.ecommerce.repository.ProdutoRepository;
import br.ifrn.edu.jeferson.ecommerce.specification.ProdutoSpecification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ProdutoService {
    private static final Logger logger = LoggerFactory.getLogger(ProdutoService.class);

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoMapper produtoMapper;

    @Autowired
    private CategoriaRepository categoriaRepository;

    private List<Categoria> buscarCategoriasPorId(List<Long> categoriaIds) {
        return categoriaRepository.findAllById(categoriaIds);
    }

    public ProdutoResponseDTO salvar(ProdutoRequestDTO produtoDto) {
        logger.info("Iniciando salvamento de novo produto");
        var produto =  produtoMapper.toEntity(produtoDto);
        var categorias = buscarCategoriasPorId(produtoDto.getCategoriaIds());        
        produto.setCategorias(categorias);
        produtoRepository.save(produto);
        logger.info("Produto salvo com sucesso: {}", produto.getId());
        return produtoMapper.toResponseDTO(produto);
    }

    public Page<ProdutoResponseDTO> lista(
        Pageable pageable,
        String nome,
        BigDecimal precoMaiorQue,
        BigDecimal precoMenorQue
    ){
        logger.info("Listando produtos com filtros - Nome: {}, Preço Maior Que: {}, Preço Menor Que: {}", nome, precoMaiorQue, precoMenorQue);
        Specification<Produto> spec = Specification.where(ProdutoSpecification.comNomeContendo(nome))
            .and(ProdutoSpecification.comPrecoMaiorQue(precoMaiorQue))
            .and(ProdutoSpecification.comPrecoMenorQue(precoMenorQue));

        Page<Produto> produtos = produtoRepository.findAll(spec, pageable);
        logger.info("Produtos listados com sucesso");
        return produtoMapper.toDTOPage(produtos);
    }

    public void deletar(Long id) {
        logger.info("Deletando produto com ID: {}", id);
        if (!produtoRepository.existsById(id)) {
            logger.error("Produto com ID: {} não encontrado", id);
            throw new ResourceNotFoundException("Produto não encontrado");
        }
        produtoRepository.deleteById(id);
        logger.info("Produto deletado com sucesso");
    }

    public ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO produtoDto) {
        logger.info("Atualizando produto com ID: {}", id);
        Produto produto = produtoRepository.findById(id).orElseThrow( () -> {
            logger.error("Produto com ID: {} não encontrado", id);
            return new ResourceNotFoundException("Produto não encontrado");
        });

        produtoMapper.updateEntityFromDTO(produtoDto, produto);
        var produtoAlterado = produtoRepository.save(produto);
        logger.info("Produto atualizado com sucesso: {}", produtoAlterado.getId());

        return produtoMapper.toResponseDTO(produtoAlterado);
    }

    public ProdutoResponseDTO buscarPorId(Long id) {
        logger.info("Buscando produto com ID: {}", id);
        Produto produto = produtoRepository.findById(id).orElseThrow( () -> {
            logger.error("Produto com ID: {} não encontrado", id);
            return new ResourceNotFoundException("Produto não encontrado");
        });
        logger.info("Produto encontrado: {}", produto.getId());
        return produtoMapper.toResponseDTO(produto);
    }

    public ProdutoResponseDTO atualizarEstoque(Long id, ProdutoAtualizarEstoqueRequestDTO quantidade) {
        logger.info("Atualizando estoque do produto com ID: {}", id);
        Produto produto = produtoRepository.findById(id).orElseThrow( () -> {
            logger.error("Produto com ID: {} não encontrado", id);
            return new ResourceNotFoundException("Produto não encontrado");
        });
        produto.setEstoque(quantidade.getQuantidade());
        var produtoAlterado = produtoRepository.save(produto);
        logger.info("Estoque do produto atualizado com sucesso: {}", produtoAlterado.getId());

        return produtoMapper.toResponseDTO(produtoAlterado);
    }

    public List<ProdutoResponseDTO> buscarPorCategoria(Long id) {
        logger.info("Buscando produtos pela categoria com ID: {}", id);
        List<Produto> produtos = produtoRepository.findByCategorias_Id(id);
        logger.info("Produtos encontrados: {}", produtos.size());
        return produtoMapper.toDTOList(produtos);
    }
}
