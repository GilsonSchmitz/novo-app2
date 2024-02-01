package br.com.gilson.novoapp.service;

import br.com.gilson.novoapp.dto.ItemPedidoDto;
import br.com.gilson.novoapp.model.entity.ItemPedido;
import br.com.gilson.novoapp.model.repository.ItemPedidoRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemPedidoService {

    @Autowired
    ItemPedidoRepository repository;

    @Transactional
    public ItemPedido buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    @Transactional
    public ItemPedido salvarItemPedido(ItemPedido itemPedido) {
        return repository.save(itemPedido);
    }

    @Transactional
    public ItemPedido atualizar(ItemPedido itemPedido) {
        ItemPedido pedidoAtualizar = repository.findById(itemPedido.getId()).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        pedidoAtualizar.setProduto(itemPedido.getProduto());
        pedidoAtualizar.setPedido(itemPedido.getPedido());
        pedidoAtualizar.setValor(itemPedido.getValor());
        pedidoAtualizar.setQuantidade(itemPedido.getQuantidade());
        return repository.save(itemPedido);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}