package br.com.darlison.portifolio.application.rest;

import br.com.darlison.portifolio.application.model.input.PersonRequest;
import br.com.darlison.portifolio.domain.model.Page;
import br.com.darlison.portifolio.domain.model.Person;
import br.com.darlison.portifolio.domain.port.usecase.person.GetPersonUseCase;
import br.com.darlison.portifolio.domain.port.usecase.person.SavePersonUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PersonController.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SavePersonUseCase savePersonUseCase;

    @MockBean
    private GetPersonUseCase getPersonUseCase;

    @Autowired
    private ObjectMapper objectMapper;

    private static EasyRandom easyRandom;

    @BeforeAll
    private static void beforeTests() {
        easyRandom = new EasyRandom();
    }

    @Test
    public void testCreatePerson() throws Exception {
        PersonRequest request = easyRandom.nextObject(PersonRequest.class);
        request.setCpf("978.956.270-58");
        request.setBirthDate(LocalDate.of(1990, 1, 1));

        ArgumentCaptor<Person> personCaptor = ArgumentCaptor.forClass(Person.class);

        mockMvc.perform(
                        post("/v1/person")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()
                );

        verify(savePersonUseCase).save(personCaptor.capture());
        Person savedPerson = personCaptor.getValue();

        assertEquals(request.getName(), savedPerson.getName());
        assertEquals(request.getBirthDate(), savedPerson.getBirthDate());
        assertEquals(request.getCpf(), savedPerson.getCpf());
        assertEquals(request.isWorker(), savedPerson.getWorker());
    }

    @Test
    public void testListPersons() throws Exception {
        List<Person> persons = Arrays.asList(
                easyRandom.nextObject(Person.class),
                easyRandom.nextObject(Person.class)
        );
        persons.get(0).setBirthDate(LocalDate.of(1990, 3, 5));
        persons.get(0).setCreatedAt(LocalDateTime.now());
        persons.get(0).setUpdatedAt(LocalDateTime.now());
        persons.get(1).setBirthDate(LocalDate.of(1990, 3, 5));
        persons.get(1).setCreatedAt(LocalDateTime.now());
        persons.get(1).setUpdatedAt(LocalDateTime.now());
        Page<Person> page = new Page<>(persons, 2);

        when(getPersonUseCase.findAll(anyString(), anyInt(), anyInt())).thenReturn(page);

        mockMvc.perform(get("/v1/person"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[0].id", is(persons.get(0).getId().toString())))
                .andExpect(jsonPath("$.content[0].name", is(persons.get(0).getName())))
                .andExpect(jsonPath("$.content[0].birthDate", is("05-03-1990")))
                .andExpect(jsonPath("$.content[1].cpf", is(persons.get(0).getCpf())))
                .andExpect(jsonPath("$.content[1].worker", is(persons.get(0).getWorker())));

        // Verifique se o método findAll foi chamado no getPersonUseCase com os parâmetros corretos
        verify(getPersonUseCase).findAll(null, 0, 10);
    }

    @Test
    public void testFindPersonById() throws Exception {
        UUID personId = UUID.randomUUID();
        Person person = easyRandom.nextObject(Person.class);
        person.setBirthDate(LocalDate.of(1990, 1, 10));

        when(getPersonUseCase.findById(personId)).thenReturn(person);

        mockMvc.perform(get("/v1/person/{id}", personId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(person.getId().toString())))
                .andExpect(jsonPath("$.name", is(person.getName())))
                .andExpect(jsonPath("$.birthDate", is("10-01-1990")))
                .andExpect(jsonPath("$.cpf", is(person.getCpf())))
                .andExpect(jsonPath("$.worker", is(person.getWorker())));

        verify(getPersonUseCase).findById(personId);
    }

}