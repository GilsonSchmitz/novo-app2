package br.com.gilson.novoapp.repository;

import br.com.gilson.novoapp.model.entity.Produto;
import br.com.gilson.novoapp.model.repository.ProdutoRepository;
import br.com.gilson.novoapp.service.ProdutoService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;


@SpringBootTest
@AutoConfigureTestEntityManager
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY, connection = EmbeddedDatabaseConnection.H2)
@ActiveProfiles("test")
public class ProdutoRepositoryTest {

    @Autowired
    ProdutoService service;

    @Autowired
    ProdutoRepository repository;

    @AfterEach
    void AfterEach() {
        repository.deleteAll();
    }

    @Test
    @DisplayName("Buscar Produto Repository")
    public void testBuscarPorProdutoRepository() {
        Produto produto = repository.save(Produto.builder().nome("Lucas").valor(2713.00).build());

        Produto produtoFinded = repository.findById(produto.getId()).get();

        Assertions.assertEquals(produto.getId(), produtoFinded.getId());
        Assertions.assertEquals(produto.getNome(), produtoFinded.getNome());
        Assertions.assertEquals(produto.getValor(), produtoFinded.getValor());
    }

    @Test
    @DisplayName("Salvar Produto Repository")
    public void testSalvarProdutoRepository() {
        Produto produto = repository.save(Produto.builder().nome("Lucas").valor(2039.00).build());
        Produto produtoAchado = repository.findById(produto.getId()).get();

        Assertions.assertNotNull(produto.getId());
        Assertions.assertEquals(produtoAchado.getNome(), produto.getNome());
        Assertions.assertEquals(produtoAchado.getValor(), produto.getValor());
    }

    @Test
    @DisplayName("Deletar Produto Repository")
    public void testDeletarProdutoRepository() {
        Produto produto = repository.save(Produto.builder().nome("Lucas").valor(9283.00).build());
        Long produtoId = produto.getId();
        repository.deleteById(produtoId);

        Optional<Produto> produtoOptional = repository.findById(produtoId);
    }

    @Test
    @DisplayName("Atualizar Cliente Repository")
    public void testUpdatedRepository() {
        Produto produto = repository.save(Produto.builder().nome("Lucas").valor(12312.00).build());
        Long produtoId = produto.getId();

        Produto produtoAtualizar = repository.save(Produto.builder().id(produtoId).nome("Lucas").valor(12312.01).build());

        Assertions.assertEquals(produtoAtualizar.getId(), produto.getId());
        Assertions.assertEquals(produtoAtualizar.getNome(), produto.getNome());
        Assertions.assertNotEquals(produtoAtualizar.getValor(), produto.getValor());

    }
}


//    @Test
//    @DisplayName("Obter produto por ID")
//    public void obterPorId() {
//        Long id = 1l;
//        Produto produto = Produto.builder().id(1l).nome("Gas").valor(20.0).build();
//
//        Mockito.when(repository.findById(produto.getId())).thenReturn(Optional.of(produto));
////        produto = repository.save(produto);
//        Long produtoId = produto.getId();
//        Produto produtoNovo = repository.findById(produtoId).orElse(null);
//
//        assertThat(produtoNovo).isNotNull();
//        assertThat(produtoNovo.getId()).isEqualTo(produtoId);
//        assertThat(produtoNovo.getNome()).isEqualTo(produto.getNome());
//        assertThat(produtoNovo.getValor()).isEqualTo(produto.getValor());
//    }
//
//    @Test
//    @DisplayName("Deletar teste")
//    public void deletarTeste() {
//
//        Long id = 1l;
//
//        Produto produto = Produto.builder().id(id).nome("Gas").valor(300.00).build();
//        Mockito.doNothing().when(repository).deleteById(id);
//        repository.deleteById(id);
//
//        verify(repository, times(1)).deleteById(1l);
//    }
//
//    @Test
//    @DisplayName("Update test")
//    public void updateTest() {
//
//        Long id = 1l;
//        Long id2 = 2l;
//        Produto novoProduto = Produto.builder().id(id).nome("Agua").valor(20.00).build();
//        Produto produto = Produto.builder().id(id).nome("Gas").valor(300.00).build();
//        Mockito.when(repository.findById(produto.getId())).thenReturn(Optional.of(produto));
//        repository.save(produto);
//        service.update(id, novoProduto);
//
//
//        assertThat(produto.getId()).isEqualTo(novoProduto.getId());
//        assertThat(produto.getValor()).isEqualTo(novoProduto.getValor());
//        assertThat(produto.getNome()).isEqualTo(novoProduto.getNome());

