package br.com.gilson.novoapp.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDto {

    private Long id;
    @NotEmpty
    private String nome;
    @NotEmpty
    private Double valor;


}
