package br.com.gilson.novoapp.mapper;

import br.com.gilson.novoapp.dto.PedidoDto;
import br.com.gilson.novoapp.model.entity.Pedido;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PedidoMapper {

    PedidoDto toDto(Pedido pedido);

    @InheritInverseConfiguration
    Pedido toEntity(PedidoDto pedidoDto);


}
