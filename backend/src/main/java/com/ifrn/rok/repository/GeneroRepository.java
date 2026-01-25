package com.ifrn.rok.repository;

import com.ifrn.rok.models.Genero;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GeneroRepository extends CrudRepository<Genero, Long> {

    Optional<Genero> findByNome(String nome);
}
