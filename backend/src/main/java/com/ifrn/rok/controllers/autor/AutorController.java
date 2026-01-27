package com.ifrn.rok.controllers.autor;


import com.ifrn.rok.dto.AutorDTO;
import com.ifrn.rok.models.Autor;
import com.ifrn.rok.repository.AutorRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutorController {

    @Autowired
    AutorRepository autorRepository;

    @GetMapping(value = "/autor/{id}")
    @Operation(summary = "Visualização do autor")
    public AutorDTO autorDetalhe(@PathVariable("id") Long id) {
        Autor autor = autorRepository.findById(id).get();
        return new AutorDTO(autor);
    }
}
