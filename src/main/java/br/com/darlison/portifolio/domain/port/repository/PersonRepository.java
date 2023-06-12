package br.com.darlison.portifolio.domain.port.repository;

import br.com.darlison.portifolio.domain.model.Page;
import br.com.darlison.portifolio.domain.model.Person;

import java.util.Optional;
import java.util.UUID;

public interface PersonRepository {

    UUID save(Person person);

    Page<Person> findAll(String description, int page, int limit);

    Optional<Person> findById(UUID id);

}
