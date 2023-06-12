package br.com.darlison.portifolio.infrastructure.map;

import br.com.darlison.portifolio.domain.model.Person;
import br.com.darlison.portifolio.infrastructure.database.entity.PersonEntity;

public class PersonEntityMapper {

    public static Person toPerson(PersonEntity personEntity) {
        Person person = new Person();
        person.setId(personEntity.getId());
        person.setCreatedAt(personEntity.getCreatedAt());
        person.setUpdatedAt(personEntity.getUpdatedAt());
        person.setName(personEntity.getName());
        person.setBirthDate(personEntity.getBirthDate());
        person.setCpf(personEntity.getCpf());
        person.setWorker(personEntity.getWorker());
        return person;
    }

    public static PersonEntity toPersonEntity(Person person) {
        PersonEntity personEntity = new PersonEntity();
        personEntity.setId(person.getId());
        personEntity.setCreatedAt(person.getCreatedAt());
        personEntity.setUpdatedAt(person.getUpdatedAt());
        personEntity.setName(person.getName());
        personEntity.setBirthDate(person.getBirthDate());
        personEntity.setCpf(person.getCpf());
        personEntity.setWorker(person.getWorker());
        return personEntity;
    }

}
