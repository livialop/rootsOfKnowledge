package com.ifrn.rok.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private @NotBlank @Size(max=100) String titulo;
    private @NotBlank @Size(max=50) String subTitulo;
    private @NotBlank @Min(15) BigDecimal preco;
    private @NotBlank String conteudo;
    private @NotBlank String sumario;
    private @NotBlank @Min(30) int numeroPaginas;
    private @NotBlank @Column(unique = true) String isbn;
    @ManyToOne
    private @NotBlank Autor autor;
    @ManyToOne
    private @NotBlank Genero genero;

    @Deprecated
    public Livro() {

    }

    public Livro(@NotBlank @Size(max=100) String titulo, @NotBlank @Size(max = 50) String subTitulo,
                 @Min(15) BigDecimal preco, @NotBlank String conteudo, @NotBlank String sumario,
                 @Min(30) int numeroPaginas, @NotBlank String isbn, Autor autor, Genero genero) {
        this.titulo = titulo;
        this.subTitulo = subTitulo;
        this.preco = preco;
        this.conteudo = conteudo;
        this.sumario = sumario;
        this.numeroPaginas = numeroPaginas;
        this.isbn = isbn;
        this.autor = autor;
        this.genero = genero;
    }


    // Getters
    public Autor getAutor() {
        return autor;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public Long getId() {
        return id;
    }

    public String getConteudo() {
        return conteudo;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getSubTitulo() {
        return subTitulo;
    }

    public String getSumario() {
        return sumario;
    }

    public String getTitulo() {
        return titulo;
    }

    public Genero getGenero() {
        return genero;
    }

    // Setters
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setSubTitulo(String subTitulo) {
        this.subTitulo = subTitulo;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public void setSumario(String sumario) {
        this.sumario = sumario;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }
}
