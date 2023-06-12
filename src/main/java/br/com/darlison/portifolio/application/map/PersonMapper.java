package br.com.darlison.portifolio.application.map;

import br.com.darlison.portifolio.application.model.input.PersonRequest;
import br.com.darlison.portifolio.application.model.output.PersonResponse;
import br.com.darlison.portifolio.domain.model.Person;

public class PersonMapper {

    public static PersonResponse toPersonResponse(Person person) {
        PersonResponse personResponse = new PersonResponse();
        personResponse.setId(person.getId());
        personResponse.setCreatedAt(person.getCreatedAt());
        personResponse.setUpdatedAt(person.getUpdatedAt());
        personResponse.setName(person.getName());
        personResponse.setBirthDate(person.getBirthDate());
        personResponse.setCpf(person.getCpf());
        personResponse.setWorker(person.getWorker());
        return personResponse;
    }

    public static Person toPerson(PersonRequest personRequest) {
        Person person = new Person();
        person.setCpf(personRequest.getCpf());
        person.setName(personRequest.getName());
        person.setWorker(personRequest.isWorker());
        person.setBirthDate(personRequest.getBirthDate());
        return person;
    }

}
