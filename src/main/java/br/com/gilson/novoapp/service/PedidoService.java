package br.com.gilson.novoapp.service;

import br.com.gilson.novoapp.model.entity.Cliente;
import br.com.gilson.novoapp.model.entity.Pedido;
import br.com.gilson.novoapp.model.entity.Produto;
import br.com.gilson.novoapp.model.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository repository;

    private final ClienteService clienteService;

    private final ProdutoService produtoService;


    @Transactional
    public Pedido buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    @Transactional
    public Pedido salvar(Pedido pedido) {
        Cliente cliente = clienteService.buscarPor(pedido.getCliente().getId());
        pedido.setCliente(cliente);
        pedido.getListaItens().forEach(itemPedido -> {
            Produto produto = produtoService.buscarPor(itemPedido.getProduto().getId());
            itemPedido.setProduto(produto);
            itemPedido.setPedido(pedido);
        });
        return repository.save(pedido);
    }

    @Transactional
    public Pedido atualizar(Long id, Pedido pedido) {
        Pedido pedidoAchado = repository.findById(pedido.getId()).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        pedidoAchado.setCliente(pedido.getCliente());
        pedidoAchado.setDataPedido(pedido.getDataPedido());
        pedidoAchado.setListaItens(pedido.getListaItens());
        return repository.save(pedido);
    }
    @Transactional
    public List<Pedido> retornoLista() {
        return repository.findAll();
    }

    @Transactional
    public void deletarPorId(Long id) {
        repository.deleteById(id);
    }
}
