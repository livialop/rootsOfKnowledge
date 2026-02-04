package com.ifrn.rok.controllers.genero;


import com.ifrn.rok.dto.GenerosDTO;
import com.ifrn.rok.models.Genero;
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

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
public class GenerosController {

    @Autowired
    GeneroRepository generoRepository;

    @Autowired
    LivroRepository livroRepository;

    @GetMapping(value = "/generos")
    @Operation(summary = "Página de visualização dos gêneros")
    public Collection<GenerosDTO> list() {
        return generoRepository.findAll().stream()
                .map(GenerosDTO:: new)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/genero/{id}")
    @Operation(summary = "Visualização de um gênero específico.")
    public GenerosDTO generoDetalhe(@PathVariable("id") Long id) {
        Genero genero = generoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Gênero não encontrado."));
        return new GenerosDTO(genero);
    }

    @PutMapping(value = "/genero/{id}")
    @Transactional
    @Operation(summary = "Edição de um gênero específico.")
    public ResponseEntity<GenerosDTO> editarGenero(@PathVariable("id") Long id, @RequestBody @Valid NovoGeneroForm form) {
        Genero genero = generoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Gênero não encontrado."));
        
        // Atualizar o campo nome do gênero existente
        genero.setNome(form.getNome());
        
        Genero generoSalvo = generoRepository.save(genero);
        
        return ResponseEntity.ok(new GenerosDTO(generoSalvo));
    }

    @DeleteMapping(value = "/genero/{id}")
    @Transactional
    @Operation(summary = "Exclusão de um gênero específico.")
    public ResponseEntity<?> deletarGenero(@PathVariable("id") Long id) {
        if (!generoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        // Verifica se há livros associados ao gênero
        long countLivros = livroRepository.findAll().stream()
                .filter(livro -> livro.getGenero().getId().equals(id))
                .count();
        
        if (countLivros > 0) {
            return ResponseEntity.badRequest()
                    .body("Não é possível excluir este gênero pois existem " + countLivros + " livro(s) associado(s) a ele.");
        }
        
        generoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
