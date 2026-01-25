package com.ifrn.rok.controllers.autor;

import com.ifrn.rok.models.Autor;
import com.ifrn.rok.repository.AutorRepository;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class NovoAutorController {

    @Autowired
    AutorRepository autorRepository;

    @PostMapping("/novoautor")
    @Transactional
    @Operation(summary = "Novo autor")
    public void NovoAutor(@ModelAttribute @Valid NovoAutorForm form) {
        boolean autorJaExiste = autorRepository.findByNome(form.getNome()).isPresent();

        if (autorJaExiste) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Autor com mesmo nome já existe.");
        }

        Autor autor = form.novoAutor();
        autorRepository.save(autor);
    }
}
