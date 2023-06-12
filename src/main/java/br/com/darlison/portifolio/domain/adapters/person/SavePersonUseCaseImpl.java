package br.com.darlison.portifolio.domain.adapters.person;

import br.com.darlison.portifolio.domain.exceptions.PersonNotFoundException;
import br.com.darlison.portifolio.domain.model.Person;
import br.com.darlison.portifolio.domain.port.repository.PersonRepository;
import br.com.darlison.portifolio.domain.port.usecase.person.SavePersonUseCase;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SavePersonUseCaseImpl implements SavePersonUseCase {

    private final PersonRepository personRepository;

    public SavePersonUseCaseImpl(final PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void save(final Person person) {
        Person toSave = Objects.isNull(person.getId())
                ? new Person()
                : personRepository.findById(person.getId())
                .orElseThrow(PersonNotFoundException::new);

        toSave.setBirthDate(person.getBirthDate());
        toSave.setCpf(person.getCpf());
        toSave.setName(person.getName());
        toSave.setWorker(person.getWorker());

        personRepository.save(toSave);
    }
}
