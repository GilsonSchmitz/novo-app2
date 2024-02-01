package br.com.gilson.novoapp.dto;


import br.com.gilson.novoapp.model.entity.ItemPedido;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PedidoDto {


    private Long id;

    @NotEmpty
    private ClienteDto cliente;

    @NotEmpty
    private List<ItemPedidoDto> listaItens;

    @NotEmpty
    private Date dataPedido;

}


