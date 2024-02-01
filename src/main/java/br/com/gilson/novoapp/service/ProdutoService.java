package br.com.gilson.novoapp.service;

import br.com.gilson.novoapp.model.entity.Produto;
import br.com.gilson.novoapp.model.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    @Transactional
    public List<Produto> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Produto buscarPor(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    @Transactional
    public Produto save(Produto produto) {
        return repository.save(produto);
    }

    @Transactional
    public Produto update(Long id, Produto produto) {
        Produto produtoFinded = repository.findById(id).orElseThrow(() -> new RuntimeException("Não encontrado"));
        produtoFinded.setNome(produto.getNome());
        produtoFinded.setValor(produto.getValor());
        return repository.save(produto);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }


}




