package br.com.gilson.novoapp.service;

import br.com.gilson.novoapp.model.entity.Cliente;
import br.com.gilson.novoapp.model.entity.Produto;
import br.com.gilson.novoapp.model.repository.ClienteRepository;
import br.com.gilson.novoapp.model.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.ID;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public Produto buscarPor(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    public Produto save(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto update(Long id, Produto produto) {
        Produto produtoFinded = produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Não encontrado"));
        produtoFinded.setNome(produto.getNome());
        produtoFinded.setValor(produto.getValor());
        return produtoRepository.save(produto);
    }

public Produto delete(Produto produto){
   produtoRepository.delete(produto);
   return produto;



    }

}




