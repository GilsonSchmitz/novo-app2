package br.com.gilson.novoapp.mapper;

import br.com.gilson.novoapp.dto.ItemPedidoDto;
import br.com.gilson.novoapp.model.entity.ItemPedido;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemPedidoMapper {

    ItemPedidoDto toDto(ItemPedido itemPedido);


    @InheritInverseConfiguration
    ItemPedido toEntity(ItemPedidoDto itemPedidoDto);

}