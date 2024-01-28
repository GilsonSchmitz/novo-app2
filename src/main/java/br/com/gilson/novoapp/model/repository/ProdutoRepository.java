package br.com.gilson.novoapp.model.repository;

import br.com.gilson.novoapp.model.entity.Produto;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}