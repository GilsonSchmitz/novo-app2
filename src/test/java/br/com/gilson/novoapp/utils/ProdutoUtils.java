package br.com.gilson.novoapp.utils;

import br.com.gilson.novoapp.model.entity.Produto;

public class ProdutoUtils {

    public Produto retornarObjeto(Long id) {
        return Produto.builder()
                .id(id)
                .nome("Gas")
                .valor(23.0)
                .build();
    }
}
