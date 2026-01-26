package com.ifrn.rok.dto;

import com.ifrn.rok.models.Genero;

public class GenerosDTO {

    private String nome;

    public GenerosDTO(Genero genero) {
        this.nome = genero.getNome();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
