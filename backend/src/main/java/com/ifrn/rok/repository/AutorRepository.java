package com.ifrn.rok.repository;

import com.ifrn.rok.models.Autor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface AutorRepository extends CrudRepository<Autor, Long> {

    Optional<Autor> findByNome(String nome);

    @Override
    Collection<Autor> findAll();
}
