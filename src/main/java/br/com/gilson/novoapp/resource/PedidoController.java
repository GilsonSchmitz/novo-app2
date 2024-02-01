package br.com.gilson.novoapp.resource;

import org.springframework.transaction.annotation.Transactional;
import br.com.gilson.novoapp.dto.PedidoDto;
import br.com.gilson.novoapp.mapper.PedidoMapper;
import br.com.gilson.novoapp.model.entity.Pedido;
import br.com.gilson.novoapp.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/pedidos")
@Validated
public class PedidoController {

    @Autowired
    PedidoMapper mapper;

    @Autowired
    PedidoService service;

    @Transactional
    @GetMapping(value = {"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public PedidoDto obterPedido(@PathVariable Long id) {
        return mapper.toDto(service.buscarPorId(id));
    }

    @Transactional
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoDto inserir(@RequestBody PedidoDto pedidoDto) {
        Pedido pedido = mapper.toEntity(pedidoDto);
        return mapper.toDto(service.salvar(pedido));
    }

    @Transactional
    @PutMapping(value = {"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public PedidoDto atualizarProduto(@PathVariable Long id, @RequestBody PedidoDto pedidoDto) {
        Pedido pedido = mapper.toEntity(pedidoDto);
        return mapper.toDto(service.atualizar(id, pedido));
    }

    @Transactional
    @DeleteMapping(value = {"/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirProduto(@PathVariable Long id) {
        service.deletarPorId(id);
    }
}