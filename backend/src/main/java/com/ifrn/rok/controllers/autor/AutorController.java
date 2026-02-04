package com.ifrn.rok.controllers.autor;


import com.ifrn.rok.dto.AutorDTO;
import com.ifrn.rok.repository.AutorRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
public class AutorController {

    @Autowired
    AutorRepository autorRepository;

    @GetMapping(value = "/autores")
    @Operation(summary = "Visualização de autores")
    public Collection<AutorDTO> list() {
        return autorRepository.findAll().stream()
                .map(AutorDTO:: new)
                .collect(Collectors.toList());
    }
}
