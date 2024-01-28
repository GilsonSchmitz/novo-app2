package br.com.gilson.novoapp.service;

import br.com.gilson.novoapp.model.entity.Produto;
import br.com.gilson.novoapp.model.repository.ProdutoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ActiveProfiles("test")
@SpringBootTest
public class ProdutoServiceTest {

    @Autowired
    private ProdutoService service;

    @MockBean
    ProdutoRepository repository;

    @Test
    @DisplayName("Atualizar produto")
    public void testUpdateService() {

        Long id = 1l;
        Produto produto = Produto.builder().id(id).nome("Gas").valor(23.0).build();
        Mockito.when(repository.findById(produto.getId())).thenReturn(Optional.of(produto));
        Mockito.when(repository.save(produto)).thenReturn(produto);
        Produto produtoAtualizar = Produto.builder().id(id).nome("Gas").valor(23.0).build();
        Produto novoproduto = service.update(id, produto);

        assertThat(produto.getNome()).isEqualTo(produtoAtualizar.getNome());
        assertThat(produto.getValor()).isEqualTo(produtoAtualizar.getValor());
        assertThat(produto.getId()).isEqualTo(produtoAtualizar.getId());
    }

    @Test
    @DisplayName("Salvar produto")
    public void testSalvarService() {
        Produto produto = Produto.builder().id(1L).nome("Gas").valor(23.0).build();
        Mockito.when(repository.save(produto)).thenReturn(produto);
        Produto produtoAtualizado = service.save(produto);
        assertThat(produtoAtualizado).isNotNull();

        assertThat(produtoAtualizado.getValor()).isEqualTo(produto.getValor());
        assertThat(produtoAtualizado.getNome()).isEqualTo(produto.getNome());
    }

    @Test
    @DisplayName("Deletar produto")
    public void testDeletarService() {

        Long id = 1l;
        Produto produto = Produto.builder().id(id).nome("Gas").valor(23.0).build();
        Mockito.doNothing().when(repository).deleteById(id);
        service.delete(id);

        verify(repository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("Buscar por cliente")
    public void testBuscarService() {

        Long id = 1l;
        Produto produto = Produto.builder().id(id).nome("Gas").valor(23.0).build();
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(produto));
        Produto produtoBuscado = service.buscarPor(id);

        assertThat(produto.getNome()).isEqualTo(produtoBuscado.getNome());
        assertThat(produto.getValor()).isEqualTo(produtoBuscado.getValor());
        assertThat(produto.getId()).isEqualTo(produtoBuscado.getId());
    }


}

