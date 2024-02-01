package br.com.gilson.novoapp.utils;

import br.com.gilson.novoapp.model.entity.Cliente;
import br.com.gilson.novoapp.model.entity.ItemPedido;
import br.com.gilson.novoapp.model.entity.Pedido;
import br.com.gilson.novoapp.model.entity.Produto;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PedidoUtils {

    public Pedido retornarObjeto() {
        return Pedido.builder().id(14l)
                .dataPedido(Date.valueOf(LocalDate.now()))
                .cliente(Cliente.builder().id(1L).nome("Lucas").cpf("123123-44").email("juninho@email.com").build())
                .listaItens(retornarItens())
                .build();
    }

    public List<ItemPedido> retornarItens() {
        List<ItemPedido> itens = new ArrayList<>();
        ItemPedido itemPedido = ItemPedido.builder().id(13l)
                .produto(Produto.builder().id(1L).valor(123.2).nome("Sacola").build()).id(3l)
                .quantidade(12l)
                .valor(54.4)
                .build();
        ItemPedido itemPedido2 = ItemPedido.builder().id(11l)
                .produto(Produto.builder().id(2l).nome("bolsa").valor(123.0).build()).id(4l)
                .quantidade(2L)
                .valor(88.4)
                .build();
        itens.add(itemPedido);
        itens.add(itemPedido2);
        return itens;


    }
}