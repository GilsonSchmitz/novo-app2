package br.com.gilson.novoapp.mapper;

import br.com.gilson.novoapp.dto.ClienteDto;
import br.com.gilson.novoapp.model.entity.Cliente;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteDto toDto(Cliente cliente);

    @InheritInverseConfiguration
    Cliente toEntity(ClienteDto dto);

}
