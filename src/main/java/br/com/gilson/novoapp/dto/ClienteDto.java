package br.com.gilson.novoapp.dto;

import jakarta.persistence.CascadeType;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDto {



    private Long id;
    @NotEmpty
    private String nome;
    @NotEmpty
    private String cpf;


}
