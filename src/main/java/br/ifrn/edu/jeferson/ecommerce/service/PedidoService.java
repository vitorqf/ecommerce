package br.ifrn.edu.jeferson.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifrn.edu.jeferson.ecommerce.domain.Pedido;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.PedidoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.PedidoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.exception.ResourceNotFoundException;
import br.ifrn.edu.jeferson.ecommerce.mapper.PedidoMapper;
import br.ifrn.edu.jeferson.ecommerce.repository.PedidoRepository;

@Service
public class PedidoService {
    
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired  
    private PedidoMapper pedidoMapper;

    public PedidoResponseDTO salvar(PedidoRequestDTO pedidoDto) {
        var pedido =  pedidoMapper.toEntity(pedidoDto);
        pedidoRepository.save(pedido);
        return pedidoMapper.toResponseDTO(pedido);
    }

    public List<PedidoResponseDTO> lista(){
        List<Pedido> pedidos = pedidoRepository.findAll();
        return pedidoMapper.toDTOList(pedidos);
    }

    public void deletar(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Pedido não encontrado");
        }
        pedidoRepository.deleteById(id);
    }

    public PedidoResponseDTO atualizar(Long id, PedidoRequestDTO pedidoDto) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Pedido não encontrado"));

        pedidoMapper.updateEntityFromDTO(pedidoDto, pedido);
        var pedidoAlterado = pedidoRepository.save(pedido);

        return pedidoMapper.toResponseDTO(pedidoAlterado);
    }

    public PedidoResponseDTO buscarPorId(Long id) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Pedido não encontrado"));
        return pedidoMapper.toResponseDTO(pedido);
    }

    public List<PedidoResponseDTO> listarPedidosPorCliente(Long clientId) {
        List<Pedido> pedidos = pedidoRepository.findByClienteId(clientId);
        return pedidoMapper.toDTOList(pedidos);
    }
}
