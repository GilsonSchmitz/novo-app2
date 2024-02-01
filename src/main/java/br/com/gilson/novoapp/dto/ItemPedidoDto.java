package br.com.gilson.novoapp.dto;

import br.com.gilson.novoapp.model.entity.Pedido;
import br.com.gilson.novoapp.model.entity.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ItemPedidoDto {

    private Long id;


    @NotEmpty
    private ProdutoDto produto;

    @NotEmpty
    private Long quantidade;

    @NotEmpty
    private Double valor;

}
