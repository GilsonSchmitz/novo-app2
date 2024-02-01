package br.com.gilson.novoapp.model.repository;

import br.com.gilson.novoapp.model.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository <Pedido, Long> {
}
