package br.com.gilson.novoapp.resource;

import br.com.gilson.novoapp.dto.ProdutoDto;
import br.com.gilson.novoapp.mapper.ProdutoMapper;
import br.com.gilson.novoapp.model.entity.Produto;
import br.com.gilson.novoapp.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    ProdutoMapper produtoMapper;

    @Autowired
    ProdutoService produtoService;

    @DeleteMapping(value = {"/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirProduto(@PathVariable Long id) {
        produtoService.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoDto inserir(@RequestBody ProdutoDto produtoDto) {
        Produto produto = produtoMapper.toEntity(produtoDto);
        return produtoMapper.toDto(produtoService.save(produto));
    }

    @GetMapping(value = {"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ProdutoDto obterProduto(@PathVariable Long id) {
        return produtoMapper.toDto(produtoService.buscarPor(id));
    }

    @PutMapping(value = {"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ProdutoDto atualizarProduto(@PathVariable Long id, @RequestBody ProdutoDto produtoDto) {
        Produto produto = produtoMapper.toEntity(produtoDto);
        return produtoMapper.toDto(produtoService.update(id, produto));
    }
}

