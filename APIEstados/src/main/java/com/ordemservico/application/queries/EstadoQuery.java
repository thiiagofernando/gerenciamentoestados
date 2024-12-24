package com.ordemservico.application.queries;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoQuery {
    private Integer id;
    private String nome;
    private String uf;
    private boolean ativo;
}