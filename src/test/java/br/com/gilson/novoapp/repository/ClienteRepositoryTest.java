package br.com.gilson.novoapp.repository;

import br.com.gilson.novoapp.model.entity.Cliente;
import br.com.gilson.novoapp.model.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@SpringBootTest
@AutoConfigureTestEntityManager
@ActiveProfiles("test")
//@SpringBootTest
public class ClienteRepositoryTest {

    @Autowired
    ClienteRepository repository;

    @BeforeEach
    void beforeEach() {
        repository.deleteAll();
    }

    @Test
    @DisplayName("Buscar Cliente Repository")
    public void testBuscarPorIdRepository() {
        Cliente cliente = Cliente.builder().nome("Lucas").cpf("111222333-44").build();
        Cliente clienteSaved = repository.save(cliente);

        Cliente clienteFinded = repository.findById(cliente.getId()).get();

        Assertions.assertEquals(clienteSaved.getId(), clienteFinded.getId());
        Assertions.assertEquals(clienteSaved.getNome(), clienteFinded.getNome());
        Assertions.assertEquals(clienteSaved.getCpf(), clienteFinded.getCpf());
    }

    @Test
    @DisplayName("Salvar Cliente Repository")
    public void testSalvarRepository() {
        Cliente cliente = Cliente.builder().nome("Lucas").cpf("111222333-44").build();
        Cliente clienteSaved = repository.save(cliente);

        Assertions.assertNotNull(clienteSaved.getId());
        Assertions.assertEquals(clienteSaved.getNome(), cliente.getNome());
        Assertions.assertEquals(clienteSaved.getCpf(), cliente.getCpf());
    }

    @Test
    @DisplayName("Deletar Cliente Repository")
    public void testDeletarRepository() {
        Cliente cliente = repository.save(Cliente.builder().nome("Lucas").cpf("111222333-44").build());
        Long clienteId = cliente.getId();
        repository.deleteById(clienteId);

      Optional<Cliente> clienteOptional = repository.findById(clienteId);
    }

    @Test
    @DisplayName("Atualizar Cliente Repository")
    public void testUpdateRepository(){
        Cliente cliente = repository.save(Cliente.builder().nome("Lucas").cpf("111222333-44").build());
        Long clienteId = cliente.getId();

        Cliente clienteAtualizar = repository.save(Cliente.builder().id(clienteId).nome("Lucas").cpf("111222333-33").build());

        Assertions.assertEquals(clienteAtualizar.getId(), cliente.getId());
        Assertions.assertEquals(clienteAtualizar.getNome(), cliente.getNome());
        Assertions.assertNotEquals(clienteAtualizar.getCpf(), cliente.getCpf());
    }





}