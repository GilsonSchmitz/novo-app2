package br.com.gilson.novoapp.service;

import br.com.gilson.novoapp.model.entity.Pedido;
import br.com.gilson.novoapp.utils.PedidoUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.junit.jupiter.api.Assertions;

@ActiveProfiles("test")
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY, connection = EmbeddedDatabaseConnection.H2)
@AutoConfigureMockMvc
@Sql("classpath:data/inserir-dados.sql")
class PedidoServiceTest {

    @Autowired
    private PedidoService pedidoService;

    private final PedidoUtils pedidoUtils = new PedidoUtils();

    @Test
    void salvarPedido() {
        Pedido pedido = pedidoUtils.retornarObjeto();

        Pedido pedidoSalvo = pedidoService.salvar(pedido);
        Assertions.assertNotNull(pedidoSalvo);
        Assertions.assertNotNull(pedidoSalvo.getId());
        Assertions.assertEquals(1L, pedidoSalvo.getCliente().getId());
        Assertions.assertEquals("Gilson", pedidoSalvo.getCliente().getNome());
        Assertions.assertEquals(2, pedidoSalvo.getListaItens().size());
        Assertions.assertTrue(pedidoSalvo.getListaItens().stream().anyMatch(itemPedido-> itemPedido.getProduto().getId().equals(1l)));
        Assertions.assertTrue(pedidoSalvo.getListaItens().stream().noneMatch(itemPedido-> itemPedido.getProduto().getId().equals(3l)));
    }

    @Test
    void salvarPedido_clienteNaoEncontrado() {
        Pedido pedido = pedidoUtils.retornarObjeto();
        pedido.getCliente().setId(664l);
        RuntimeException runtimeException = Assertions.assertThrows(RuntimeException.class,
                () -> pedidoService.salvar(pedido));
        Assertions.assertEquals("Registro não encontrado!", runtimeException.getMessage());
    }

    @Test
    void atualizarCliente(){
        Pedido pedido = pedidoUtils.retornarObjeto();
        pedidoService.salvar(pedido);
        Pedido pedidoNovo = pedidoUtils.retornarObjeto();
        pedidoService.atualizar(pedido.getId(), pedidoUtils.retornarObjeto());

        Assertions.assertEquals(pedido.getId(), pedidoNovo.getId());
        Assertions.assertEquals(pedido.getCliente().getNome(), pedidoNovo.getCliente().getNome());
        Assertions.assertEquals(2, pedidoNovo.getListaItens().size());
    }
}