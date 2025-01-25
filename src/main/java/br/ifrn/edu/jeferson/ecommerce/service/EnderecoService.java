package br.ifrn.edu.jeferson.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.ifrn.edu.jeferson.ecommerce.domain.Cliente;
import br.ifrn.edu.jeferson.ecommerce.domain.Endereco;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.EnderecoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.EnderecoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.exception.ResourceNotFoundException;
import br.ifrn.edu.jeferson.ecommerce.mapper.EnderecoMapper;
import br.ifrn.edu.jeferson.ecommerce.repository.EnderecoRepository;
import br.ifrn.edu.jeferson.ecommerce.repository.ClienteRepository;

@Service
public class EnderecoService {
    private static final Logger logger = LoggerFactory.getLogger(EnderecoService.class);

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoMapper mapper;

    public EnderecoResponseDTO salvar(Long clientId, EnderecoRequestDTO enderecoDto) {
        logger.info("Iniciando salvamento de endereço para cliente: {}", clientId);
        // Verifica se o cliente existe
        Cliente cliente = clienteRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));


        if (enderecoRepository.existsByClienteId(clientId)) {
            throw new ResourceNotFoundException("Cliente já possui um endereço cadastrado");
        }
        

        // Mapeia o endereço e associa ao cliente
        Endereco endereco = mapper.toEntity(enderecoDto);
        endereco.setCliente(cliente);

        enderecoRepository.save(endereco);
        logger.info("Endereço salvo com sucesso para cliente: {}", clientId);
        return mapper.toResponseDTO(endereco);
    }

    public EnderecoResponseDTO listar(Long clientId) {
        logger.info("Listando endereço para cliente: {}", clientId);
        // Verifica se o cliente existe
        if (!clienteRepository.existsById(clientId)) {
            throw new ResourceNotFoundException("Cliente não encontrado");
        }

        Endereco endereco = enderecoRepository.findByClienteId(clientId);
        System.out.println("Endereco" + endereco);
        logger.info("Endereço listado com sucesso para cliente: {}", clientId);
        return mapper.toResponseDTO(endereco);
    }

    public void deletar(Long clientId, Long id) {
        logger.info("Deletando endereço para cliente: {}", clientId);
        // Verifica se o cliente existe
        if (!clienteRepository.existsById(clientId)) {
            throw new ResourceNotFoundException("Cliente não encontrado");
        }

        // Verifica se o endereço existe e pertence ao cliente
        Endereco endereco = enderecoRepository.findByIdAndClienteId(id, clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado ou não pertence ao cliente"));

        enderecoRepository.delete(endereco);
        logger.info("Endereço deletado com sucesso para cliente: {}", clientId);
    }

    public EnderecoResponseDTO atualizar(Long clientId, Long id, EnderecoRequestDTO enderecoDto) {
        logger.info("Atualizando endereço para cliente: {}", clientId);
        // Verifica se o cliente existe
        if (!clienteRepository.existsById(clientId)) {
            throw new ResourceNotFoundException("Cliente não encontrado");
        }

        // Verifica se o endereço existe e pertence ao cliente
        Endereco endereco = enderecoRepository.findByIdAndClienteId(id, clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado ou não pertence ao cliente"));

        // Atualiza os dados do endereço
        mapper.updateEntityFromDTO(enderecoDto, endereco);
        Endereco enderecoAtualizado = enderecoRepository.save(endereco);
        logger.info("Endereço atualizado com sucesso para cliente: {}", clientId);

        return mapper.toResponseDTO(enderecoAtualizado);
    }
}
