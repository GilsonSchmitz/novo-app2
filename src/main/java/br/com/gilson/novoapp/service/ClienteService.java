package br.com.gilson.novoapp.service;

import br.com.gilson.novoapp.mapper.ClienteMapper;
import br.com.gilson.novoapp.model.entity.Cliente;
import br.com.gilson.novoapp.model.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    ClienteMapper mapper;

    @Autowired
    ClienteRepository repository;

    @Transactional
    public List<Cliente> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Cliente save(Cliente cliente) {
        return repository.save(cliente);
    }

    @Transactional
    public Cliente update(Long id, Cliente cliente) {
        Cliente clienteFinded = repository.findById(id).orElseThrow(() -> new RuntimeException("Registros nao encontrado!"));
        clienteFinded.setNome(cliente.getNome());
        clienteFinded.setCpf(cliente.getCpf());
        return repository.save(clienteFinded);
    }

    @Transactional
    public Cliente buscarPor(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Registro não encontrado!"));
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}


