package com.ifrn.rok.dto;

import com.ifrn.rok.models.Autor;

import java.net.URL;

public class AutorDTO {

    private String nome;
    private URL portfolioAutor;

    public AutorDTO(Autor autor) {
        this.nome = autor.getNome();
        this.portfolioAutor = autor.getPortfolioAutor();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public URL getPortfolioAutor() {
        return portfolioAutor;
    }

    public void setPortfolioAutor(URL portfolioAutor) {
        this.portfolioAutor = portfolioAutor;
    }
}
