package com.ordemservico.application.commands;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateEstadoCommand {
    private String nome;
    private String uf;
    private boolean ativo;
}