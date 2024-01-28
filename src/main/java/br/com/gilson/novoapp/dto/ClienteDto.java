package br.com.gilson.novoapp.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
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
