package br.com.darlison.portifolio.infrastructure.service;

import br.com.darlison.portifolio.domain.model.Page;
import br.com.darlison.portifolio.domain.model.Person;
import br.com.darlison.portifolio.domain.port.repository.PersonRepository;
import br.com.darlison.portifolio.infrastructure.database.entity.PersonEntity;
import br.com.darlison.portifolio.infrastructure.database.repository.PersonDatabaseRepository;
import br.com.darlison.portifolio.infrastructure.map.PersonEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonService implements PersonRepository {

    private final PersonDatabaseRepository databaseRepository;

    @Override
    public UUID save(final Person person) {
        PersonEntity entity = PersonEntityMapper.toPersonEntity(person);
        return databaseRepository.save(entity).getId();
    }

    @Override
    public Page<Person> findAll(final String description, final int page, final int limit) {
        org.springframework.data.domain.Page<PersonEntity> result = databaseRepository.findAllByName(description,
                PageRequest.of(page, limit));
        return new Page<>(
                result.getContent().stream()
                        .map(PersonEntityMapper::toPerson)
                        .collect(Collectors.toList()),
                result.getTotalElements()
        );
    }

    @Override
    public Optional<Person> findById(final UUID id) {
        return databaseRepository.findById(id).map(PersonEntityMapper::toPerson);
    }
}
