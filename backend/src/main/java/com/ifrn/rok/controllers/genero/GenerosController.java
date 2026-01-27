package com.ifrn.rok.controllers.genero;


import com.ifrn.rok.dto.GenerosDTO;
import com.ifrn.rok.repository.GeneroRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
public class GenerosController {

    @Autowired
    GeneroRepository generoRepository;

    @GetMapping(value = "/generos")
    @Operation(summary = "Página de visualização dos gêneros")
    public Collection<GenerosDTO> list() {
        return generoRepository.findAll().stream()
                .map(GenerosDTO:: new)
                .collect(Collectors.toList());
    }
}
