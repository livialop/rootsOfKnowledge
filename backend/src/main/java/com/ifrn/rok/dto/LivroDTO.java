package com.ifrn.rok.dto;


import com.ifrn.rok.models.Livro;

import java.math.BigDecimal;

public class LivroDTO {

    // Exibir todas as informações de um livro

    private String titulo;
    private String subTitulo;
    private BigDecimal preco;
    private String conteudo;
    private String sumario;
    private int numeroPaginas;
    private String isbn;
    private String nomeAutor;
    private String genero;
    private Long id;

    public LivroDTO(Livro livro) {
        this.titulo = livro.getTitulo();
        this.subTitulo = livro.getSubTitulo();
        this.preco = livro.getPreco();
        this.conteudo = livro.getConteudo();
        this.sumario = livro.getSumario();
        this.numeroPaginas = livro.getNumeroPaginas();
        this.isbn = livro.getIsbn();
        this.nomeAutor = livro.getAutor().getNome();
        this.genero = livro.getGenero().getNome();
        this.id = livro.getId();
    }

    public String getGenero() {
        return genero;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public Long getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public String getSubTitulo() {
        return subTitulo;
    }

    public String getSumario() {
        return sumario;
    }

    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public void setSumario(String sumario) {
        this.sumario = sumario;
    }

    public void setSubTitulo(String subTitulo) {
        this.subTitulo = subTitulo;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
