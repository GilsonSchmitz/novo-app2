package br.com.gilson.novoapp.resource;


import br.com.gilson.novoapp.dto.ClienteDto;
import br.com.gilson.novoapp.model.entity.Cliente;
import br.com.gilson.novoapp.model.repository.ClienteRepository;
import br.com.gilson.novoapp.service.ClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Objects;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY, connection = EmbeddedDatabaseConnection.H2)
@AutoConfigureMockMvc
public class ClienteControllerTest {

    static String CLIENTE_API = "/api/clientes";

    @MockBean
    ClienteRepository repository;

    @MockBean
    ClienteService service;

    @Autowired
    MockMvc mvc;

    @Test
    @DisplayName("Criar cliente normal")
    public void inserirCliente_teste() throws Exception {
        Cliente clienteSalvo = Cliente.builder().id(1L).nome("Gilson").cpf("111222333-44").build();
        BDDMockito.given(service.save(Mockito.any(Cliente.class))).willReturn(clienteSalvo);

        String json = new ObjectMapper().writeValueAsString(clienteSalvo);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(CLIENTE_API).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(json);

        mvc.perform(request).andExpect(status().isCreated()).andExpect(MockMvcResultMatchers.jsonPath("id").isNotEmpty()).andExpect(MockMvcResultMatchers.jsonPath("nome").value(clienteSalvo.getNome())).andExpect(MockMvcResultMatchers.jsonPath("cpf").value(clienteSalvo.getCpf()));
    }

    @Test
    @DisplayName("Erro ao criar cliente com valor nulo")
    public void inserirCliente_validarCamposVazios() throws Exception {
        String json = new ObjectMapper().writeValueAsString(new ClienteDto());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(CLIENTE_API).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(json);

        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isBadRequest()).andExpect(MockMvcResultMatchers.jsonPath("errors", Matchers.hasSize(2)));
    }

    @Test
    @DisplayName("teste getcliente")
    public void buscarCliente_teste() throws Exception {

        Cliente cliente = Cliente.builder().id(1l).nome("Gilson").cpf("000000000-11").build();
        Mockito.when(service.buscarPor(cliente.getId())).thenReturn(cliente);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/clientes/1").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

        mvc.perform(request).andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("id").value(1)).andExpect(MockMvcResultMatchers.jsonPath("nome").value(cliente.getNome())).andExpect(MockMvcResultMatchers.jsonPath("cpf").value(cliente.getCpf()));
    }

    @Test
    @DisplayName("getCliente_idNulo")
    public void buscarCliente_idNuloteste() throws Exception {

        Cliente cliente = Cliente.builder().id(1L).nome("Gilson").cpf("000000000-11").build();
        Mockito.when(service.buscarPor(cliente.getId())).thenThrow(new RuntimeException("Registro não encontrado"));


        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/clientes/1");

        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isNotFound()).andExpect(result -> Assertions.assertEquals("Registro não encontrado", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    @DisplayName("deletarCliente_teste")
    public void deletar_Cliente_Teste() throws Exception {
        Cliente cliente = Cliente.builder().id(1l).nome("Gilson").cpf("000000000-11").build();
        Mockito.doNothing().when(repository).deleteById(cliente.getId());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("/api/clientes/1").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isNoContent());

    }

    @Test
    @DisplayName("Atualizar cliente Test")
    public void atualizarClienteTest() throws Exception {
        Long id = 1l;
        ClienteDto clienteDtoAtualizado = ClienteDto.builder().id(1l).nome("ClienteAtualizado").cpf("301231232130-44").build();
        Cliente clienteAtualizado = Cliente.builder().id(1l).nome("ClienteAtualizado").cpf("301231232130-44").build();

        String json = new ObjectMapper().writeValueAsString(clienteDtoAtualizado);

        Mockito.when(service.update(id, clienteAtualizado)).thenReturn(clienteAtualizado);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put("/api/clientes/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("nome").value(clienteAtualizado.getNome()))
                .andExpect(MockMvcResultMatchers.jsonPath("cpf").value(clienteAtualizado.getCpf()));

    }


}


//
//
//@WebMvcTest(ProdutoController.class)
//public class ProdutoControllerTest {
//
//    @Autowired
//    private MockMvc mvc;
//
//    @Autowired
//    private ProdutoService produtoService;
//
//    @Test
//    @DisplayName("Atualizar produto")
//    public void atualizarProdutoTest() throws Exception {
//        // Dado um produto existente
//        Long produtoId = 1L;
//        ProdutoDto produtoDtoAtualizado = ProdutoDto.builder().nome("ProdutoAtualizado").valor(30.0).build();
//        Produto produtoAtualizado = Produto.builder().id(produtoId).nome("ProdutoAtualizado").valor(30.0).build();
//
//        // Configurar o Mock do Serviço para simular uma atualização bem-sucedida
//        when(produtoService.save(Mockito.any(Produto.class))).thenReturn(produtoAtualizado);
//
//        // Construir a solicitação PUT com o ID do produto
//        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
//                .put("/api/produtos/{id}", produtoId)
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//                .content(asJsonString(produtoDtoAtualizado));
//
//        // Executar a solicitação e verificar o status da resposta
//        mvc.perform(request)
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("id").value(produtoId))
//                .andExpect(MockMvcResultMatchers.jsonPath("nome").value(produtoDtoAtualizado.getNome()))
//                .andExpect(MockMvcResultMatchers.jsonPath("valor").value(produtoDtoAtualizado.getValor()));
//    }
//
//    // Método utilitário para converter um objeto para JSON
//    private static String asJsonString(Object obj) throws Exception {
//        return new ObjectMapper().writeValueAsString(obj);
//    }





