package com.ifrn.rok.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private @NotBlank @Size(max=100) String titulo;
    private @NotBlank @Size(max=50) String subtitulo;
    private @NotBlank @Min(15) BigDecimal preco;
    private @NotBlank String conteudo;
    private @NotBlank String sumario;
    private @NotBlank @Min(30) int numeroPaginas;
    private @NotBlank @Column(unique = true) String isbn;
    @ManyToOne
    private @NotBlank Autor autor;

}
