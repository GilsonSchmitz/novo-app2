package br.com.gilson.novoapp.dto;


import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;


@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDto {

    private Long id;

    private String nome;

    private Double valor;


}
