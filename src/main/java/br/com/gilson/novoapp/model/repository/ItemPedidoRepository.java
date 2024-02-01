package br.com.gilson.novoapp.model.repository;

import br.com.gilson.novoapp.model.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
}
