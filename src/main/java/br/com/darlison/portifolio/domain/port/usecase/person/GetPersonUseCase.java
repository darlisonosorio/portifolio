package br.com.darlison.portifolio.domain.port.usecase.person;

import br.com.darlison.portifolio.domain.model.Page;
import br.com.darlison.portifolio.domain.model.Person;

import java.util.UUID;

public interface GetPersonUseCase {

    Person findById(UUID id);

    Page<Person> findAll(String description, int page, int limit);

}
