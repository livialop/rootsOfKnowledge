package com.ifrn.rok.repository;

import com.ifrn.rok.models.Livro;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface LivroRepository extends CrudRepository<Livro, Long> {

    Optional<Livro> findByIsbn(String isbn);
    Optional<Livro> findByTitulo(String titulo);
    Optional<Livro> findByAutorId(long autorId);

    @Override
    Collection<Livro> findAll();
}
