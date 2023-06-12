package br.com.darlison.portifolio.infrastructure.repository.database;

import br.com.darlison.portifolio.infrastructure.database.entity.PersonEntity;
import br.com.darlison.portifolio.infrastructure.database.repository.PersonDatabaseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PersonDatabaseRepositoryTest {

    @Autowired
    private PersonDatabaseRepository personRepository;

    @Test
    public void testFindAllByNameWithNull() {
        Pageable pageable = PageRequest.of(0, 10);

        Page<PersonEntity> result = personRepository.findAllByName(null, pageable);

        assertEquals(2, result.getContent().size());
    }

    @Test
    public void testFindAllByNameWithValue() {
        Pageable pageable = PageRequest.of(0, 10);

        Page<PersonEntity> result = personRepository.findAllByName("Brando", pageable);

        assertEquals(1, result.getContent().size());
        assertEquals("Marlon Brando", result.getContent().get(0).getName());
    }
}