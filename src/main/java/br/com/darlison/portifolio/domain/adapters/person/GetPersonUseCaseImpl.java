package br.com.darlison.portifolio.domain.adapters.person;

import br.com.darlison.portifolio.domain.exceptions.PersonNotFoundException;
import br.com.darlison.portifolio.domain.model.Page;
import br.com.darlison.portifolio.domain.model.Person;
import br.com.darlison.portifolio.domain.port.repository.PersonRepository;
import br.com.darlison.portifolio.domain.port.usecase.person.GetPersonUseCase;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetPersonUseCaseImpl implements GetPersonUseCase {

    private final PersonRepository personRepository;

    public GetPersonUseCaseImpl(final PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person findById(final UUID id) {
        return personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
    }

    @Override
    public Page<Person> findAll(final String description, final int page, final int limit) {
        return personRepository.findAll(description, page, limit);
    }
}
