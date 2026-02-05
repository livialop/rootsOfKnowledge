package com.ifrn.rok.controllers.autor;


import com.ifrn.rok.dto.AutorDTO;
import com.ifrn.rok.models.Autor;
import com.ifrn.rok.repository.AutorRepository;
import com.ifrn.rok.repository.LivroRepository;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
public class  AutorController {

    @Autowired
    AutorRepository autorRepository;

    @Autowired
    LivroRepository livroRepository;

    @GetMapping(value = "/autores")
    @Operation(summary = "Visualização de autores")
    public Collection<AutorDTO> list() {
        return autorRepository.findAll().stream()
                .map(AutorDTO:: new)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/autor/{id}")
    @Operation(summary = "Buscar um autor específico por ID")
    public ResponseEntity<AutorDTO> getAutor(@PathVariable("id") Long id) {
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor não encontrado."));
        
        return ResponseEntity.ok(new AutorDTO(autor));
    }

    @PutMapping(value = "/autor/{id}")
    @Transactional
    @Operation(summary = "Edição de um autor específico.")
    public ResponseEntity<AutorDTO> editarAutor(@PathVariable("id") Long id, @RequestBody @Valid NovoAutorForm form) {
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor não encontrado."));
        
        // Atualizar os campos do autor existente
        autor.setNome(form.getNome());
        autor.setPortfolioAutor(form.getPortfolioAutor());
        
        Autor autorSalvo = autorRepository.save(autor);
        
        return ResponseEntity.ok(new AutorDTO(autorSalvo));
    }

    @DeleteMapping(value = "/autor/{id}")
    @Transactional
    @Operation(summary = "Exclusão de um autor específico.")
    public ResponseEntity<?> deletarAutor(@PathVariable("id") Long id) {
        if (!autorRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        // Verifica se há livros associados ao autor
        long countLivros = livroRepository.findAll().stream()
                .filter(livro -> livro.getAutor().getId().equals(id))
                .count();
        
        if (countLivros > 0) {
            return ResponseEntity.badRequest()
                    .body("Não é possível excluir este autor pois existem " + countLivros + " livro(s) associado(s) a ele.");
        }
        
        autorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
