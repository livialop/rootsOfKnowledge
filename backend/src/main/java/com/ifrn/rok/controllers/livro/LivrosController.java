package com.ifrn.rok.controllers.livro;


import com.ifrn.rok.dto.LivrosDTO;
import com.ifrn.rok.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
public class LivrosController {

    @Autowired
    LivroRepository livroRepository;

    @GetMapping(value = "/livros")
    public Collection<LivrosDTO> list() {
        return livroRepository.findAll().stream()
                .map(LivrosDTO:: new)
                .collect(Collectors.toList());
    }
}
