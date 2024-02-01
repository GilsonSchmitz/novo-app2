package br.com.gilson.novoapp.mapper;

import br.com.gilson.novoapp.dto.ProdutoDto;
import br.com.gilson.novoapp.model.entity.Produto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {


    ProdutoDto toDto(Produto produto);

    @InheritInverseConfiguration
    Produto toEntity(ProdutoDto produtoDto);

}

