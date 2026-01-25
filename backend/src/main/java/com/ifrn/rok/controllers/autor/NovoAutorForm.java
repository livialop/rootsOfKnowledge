package com.ifrn.rok.controllers.autor;

import com.ifrn.rok.models.Autor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.net.URL;

public class NovoAutorForm {

    @NotBlank
    @Size(max = 150)
    String nome;
    @NotBlank
    URL portfolioAutor;

    public URL getPortfolioAutor() {
        return portfolioAutor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPortfolioAutor(URL portfolioAutor) {
        this.portfolioAutor = portfolioAutor;
    }

    public Autor novoAutor() {
        return new Autor(nome, portfolioAutor);
    }
}
