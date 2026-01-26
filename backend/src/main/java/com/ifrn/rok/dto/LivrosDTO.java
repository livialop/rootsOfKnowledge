package com.ifrn.rok.dto;

import com.ifrn.rok.models.Livro;

public class LivrosDTO {

    private String titulo;
    private String genero;
    private Long id;
    private String nomeAutor;

    public LivrosDTO(Livro livro) {
        this.titulo = livro.getTitulo();
        this.genero = livro.getGenero().getNome();
        this.nomeAutor = livro.getAutor().getNome();
        this.id = livro.getId();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }
}
