package com.ifrn.rok.controllers.livro;

import com.ifrn.rok.dto.LivroDTO;
import com.ifrn.rok.models.Autor;
import com.ifrn.rok.models.Genero;
import com.ifrn.rok.models.Livro;
import com.ifrn.rok.repository.AutorRepository;
import com.ifrn.rok.repository.GeneroRepository;
import com.ifrn.rok.repository.LivroRepository;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class LivroController {

    @Autowired
    LivroRepository livroRepository;

    @Autowired
    AutorRepository autorRepository;

    @Autowired
    GeneroRepository generoRepository;

    @GetMapping(value = "/livro/{id}")
    @Operation(summary = "Visualização de um livro específico.")
    public LivroDTO livroDetalhe(@PathVariable("id") Long id) {
        Livro livro = livroRepository.findById(id).get();
        return new LivroDTO(livro);
    }

    @PutMapping(value = "/livro/{id}")
    @Transactional
    @Operation(summary = "Edição de um livro específico.")
    public ResponseEntity<LivroDTO> editarLivro(@PathVariable("id") Long id, @RequestBody @Valid NovoLivroForm form) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado."));
        
        Autor autor = autorRepository.findById(form.getAutorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Autor não encontrado."));
        Genero genero = generoRepository.findById(form.getGeneroId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Gênero não encontrado."));
        
        // Atualizar os campos do livro existente
        livro.setTitulo(form.getTitulo());
        livro.setSubTitulo(form.getSubTitulo());
        livro.setPreco(form.getPreco());
        livro.setConteudo(form.getConteudo());
        livro.setSumario(form.getSumario());
        livro.setNumeroPaginas(form.getNumeroPaginas());
        livro.setIsbn(form.getIsbn());
        livro.setAutor(autor);
        livro.setGenero(genero);
        
        Livro livroSalvo = livroRepository.save(livro);
        
        return ResponseEntity.ok(new LivroDTO(livroSalvo));
    }

    @DeleteMapping(value = "/livro/{id}")
    @Transactional
    @Operation(summary = "Exclusão de um livro específico.")
    public ResponseEntity<Void> deletarLivro(@PathVariable("id") Long id) {
        if (!livroRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        livroRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
