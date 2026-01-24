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

    @Deprecated
    public Livro() {

    }

    // Construtor
    public Livro(@NotBlank @Size(max=100) String titulo, @NotBlank @Size(max=50) String subTitulo,
                 @NotBlank @Min(15) BigDecimal preco, @NotBlank String conteudo, @NotBlank String sumario,
                 @NotBlank @Min(30) int numeroPaginas, @NotBlank String isbn, String linkCapaLivro, @NotNull Autor autor) {
        this.titulo = titulo;
        this.subTitulo = subTitulo;
        this.preco = preco;
        this.conteudo = conteudo;
        this.sumario = sumario;
        this.numeroPaginas = numeroPaginas;
        this.isbn = isbn;
        this.autor = autor;
    }

}
