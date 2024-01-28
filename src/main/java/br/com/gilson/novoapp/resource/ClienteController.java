package br.com.gilson.novoapp.resource;

import br.com.gilson.novoapp.dto.ClienteDto;
import br.com.gilson.novoapp.exception.ApiErrors;
import br.com.gilson.novoapp.mapper.ClienteMapper;
import br.com.gilson.novoapp.model.entity.Cliente;
import br.com.gilson.novoapp.model.repository.ClienteRepository;
import br.com.gilson.novoapp.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/clientes")
@Validated
public class ClienteController {

    @Autowired
    private ClienteMapper clienteMapper;

    @Autowired
    private ClienteService service;

    @Autowired
    private ClienteRepository repository;

    @GetMapping(value = {"/{id}"})
    public ClienteDto obterCliente(@PathVariable Long id) {
        return clienteMapper.toDto(service.buscarPor(id));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDto inserirCliente(@RequestBody @Valid ClienteDto dto) {
        Cliente cliente = clienteMapper.toEntity(dto);
        return clienteMapper.toDto(service.save(cliente));
    }

    @DeleteMapping(value = {"/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarCliente(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @PutMapping("/{id}")
    public ClienteDto atualizarCliente(@PathVariable Long id, @RequestBody ClienteDto dto) {
        Cliente cliente = clienteMapper.toEntity(dto);
        return clienteMapper.toDto(service.update(id, cliente));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        return new ApiErrors(bindingResult);
    }
}



