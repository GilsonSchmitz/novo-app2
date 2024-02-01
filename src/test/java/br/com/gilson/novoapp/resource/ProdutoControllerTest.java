package br.com.gilson.novoapp.resource;

import br.com.gilson.novoapp.dto.ProdutoDto;
import br.com.gilson.novoapp.model.entity.Produto;
import br.com.gilson.novoapp.model.repository.ProdutoRepository;
import br.com.gilson.novoapp.service.ProdutoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles("test")
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY, connection = EmbeddedDatabaseConnection.H2)
@AutoConfigureMockMvc
public class ProdutoControllerTest {
    static String PRODUTO_API = "/api/produtos";

    @Autowired
    MockMvc mvc;

    @Autowired
    ProdutoRepository produtoRepository;

    @Test
    @DisplayName("Criar produto normal")
    public void inserirProduto_teste() throws Exception {
        Produto produtoSalvo = Produto.builder().nome("Gilson").valor(23.0).build();


        String json = new ObjectMapper().writeValueAsString(produtoSalvo);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(PRODUTO_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("nome").value(produtoSalvo.getNome()))
                .andExpect(MockMvcResultMatchers.jsonPath("valor").value(produtoSalvo.getValor()));
    }

    @Test
    @DisplayName("Pegar produto por ID")
    public void pegarProduto_teste() throws Exception {
        Produto produtoPegar = produtoRepository.save(Produto.builder().id(1L).nome("Gilson").valor(23.0).build());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(PRODUTO_API + "/" + produtoPegar.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);



        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("nome").value(produtoPegar.getNome()))
                .andExpect(MockMvcResultMatchers.jsonPath("valor").value(produtoPegar.getValor()));

    }

    @Test
    @DisplayName("Atualizar produto Test")
    public void atualizarProdutoTest() throws Exception {
        Produto produto1 = Produto.builder().nome("Produto").valor(30.0).build();
        Produto produtoSaved = produtoRepository.save(produto1);

        ProdutoDto produtoDtoAtualizado = ProdutoDto.builder().id(produtoSaved.getId()).nome("ProdutoAtualizado").valor(30.0).build();

        String json = new ObjectMapper().writeValueAsString(produtoDtoAtualizado);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put("/api/produtos/" + produtoSaved.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(produtoSaved.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("nome").value(produtoDtoAtualizado.getNome()))
                .andExpect(MockMvcResultMatchers.jsonPath("valor").value(produtoDtoAtualizado.getValor()));
    }

    @Test
    @DisplayName("apagar produto")
    public void apagar_ProdutoTeste() throws Exception {
        Long idDoRegistro = 1L;

        mvc.perform(MockMvcRequestBuilders
                        .delete("/api/produtos/{id}", idDoRegistro)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

    }
}











