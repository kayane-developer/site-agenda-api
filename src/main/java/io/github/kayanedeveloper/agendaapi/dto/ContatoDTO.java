package io.github.kayanedeveloper.agendaapi.dto;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
public class ContatoDTO {

    @NotEmpty(message = "O nome é obrigatório.")
    private String nome;
    @NotEmpty(message = "O email é obrigatório.")
    private String email;
    @NotNull(message = "O campo 'favorito' é obrigatório.")
    private Boolean favorito;

}
