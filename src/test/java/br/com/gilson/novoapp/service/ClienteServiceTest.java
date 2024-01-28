package br.com.gilson.novoapp.service;
import br.com.gilson.novoapp.model.entity.Cliente;
import br.com.gilson.novoapp.model.repository.ClienteRepository;
import jakarta.persistence.Id;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import javax.management.Query;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ActiveProfiles("test")
@SpringBootTest
public class ClienteServiceTest {

    @Autowired
    private ClienteService service;
    @MockBean
    ClienteRepository repository;

    @Test
    @DisplayName("Salvar cliente")
    public void testeSalvarService() {

        Cliente cliente = Cliente.builder().id(1l).cpf("127391728-22").nome("Lucas").build();
        Mockito.when(repository.save(cliente)).thenReturn(cliente);

        Cliente clienteSalvo = service.save(cliente);

        assertThat(clienteSalvo.getId()).isNotNull();
        assertThat(clienteSalvo.getNome()).isEqualTo(cliente.getNome());
        assertThat(clienteSalvo.getCpf()).isEqualTo(cliente.getCpf());
    }

    @Test
    @DisplayName("Procurar por ID cliente")
    public void testProcurarService() {
        Long id = 1l;
        Cliente cliente = Cliente.builder().id(id).cpf("111222333-44").nome("Lucas").build();
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(cliente));

        Cliente clienteService = service.buscarPor(id);

        assertThat(clienteService.getId()).isEqualTo(cliente.getId());
        assertThat(clienteService.getCpf()).isEqualTo(cliente.getCpf());
        assertThat(clienteService.getNome()).isEqualTo(cliente.getNome());
    }

    @Test
    @DisplayName("Atualizar cliente")
    public void testUpdateService() {
        Long id = 1l;
        Cliente cliente = Cliente.builder().id(id).nome("Lucas").cpf("111222333-44").build();
        Mockito.when(repository.findById(id)).thenReturn(Optional.ofNullable(cliente));
        Cliente clienteNovo = Cliente.builder().id(id).nome("Lucas 2").cpf("111222333-44").build();
        Mockito.when(repository.save(clienteNovo)).thenReturn(clienteNovo);

        Cliente clienteAtualizado = service.update(id, clienteNovo);

        assertThat(clienteAtualizado.getId()).isEqualTo(clienteNovo.getId());
        assertThat(clienteAtualizado.getCpf()).isEqualTo(clienteNovo.getCpf());
        assertThat(clienteAtualizado.getNome()).isEqualTo(clienteNovo.getNome());
    }

    @Test
    @DisplayName("Deletar cliente")
    public void testDeletarService() {
        Long id = 1l;
        Cliente cliente = Cliente.builder().id(id).nome("Lucas").cpf("111222333-44").build();
        Mockito.doNothing().when(repository).deleteById(id);

        service.delete(id);

        verify(repository, times(1)).deleteById(id);

    }
}




