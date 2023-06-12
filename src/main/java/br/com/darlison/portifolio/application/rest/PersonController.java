package br.com.darlison.portifolio.application.rest;

import br.com.darlison.portifolio.application.config.swagger.annotiations.CommonResponses;
import br.com.darlison.portifolio.application.map.PersonMapper;
import br.com.darlison.portifolio.application.model.input.PersonRequest;
import br.com.darlison.portifolio.application.model.output.PersonResponse;
import br.com.darlison.portifolio.domain.model.Page;
import br.com.darlison.portifolio.domain.model.Person;
import br.com.darlison.portifolio.domain.port.usecase.person.GetPersonUseCase;
import br.com.darlison.portifolio.domain.port.usecase.person.SavePersonUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;
import java.util.stream.Collectors;

@Tag(name = "Endpoint de Pessoas")
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/v1/person")
public class PersonController {

    private final SavePersonUseCase saveUseCase;
    private final GetPersonUseCase getUseCase;

    @PostMapping
    @CommonResponses
    @Operation(
            method = "POST",
            description = "Requisição POST para salvar Pessoa",
            summary = "POST cadastrar Pessoa"
    )
    public void create(@RequestBody() @Valid final PersonRequest request) {
        saveUseCase.save(PersonMapper.toPerson(request));
    }

    @GetMapping
    @CommonResponses
    @Operation(
            method = "GET",
            description = "Requisição GET para obter a lista de Pessoas",
            summary = "GET listar Pessoas"
    )
    public Page<PersonResponse> list(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit
    ) {
        final Page<Person> result = getUseCase.findAll(name, page, limit);
        return new Page<>(
                result.getContent().stream().map(PersonMapper::toPersonResponse).collect(Collectors.toList()),
                result.getTotalElements()
        );
    }

    @GetMapping("/{id}")
    @CommonResponses
    @Operation(
            method = "GET",
            description = "Requisição GET para uma Pessoa",
            summary = "GET obter Pessoa"
    )
    public PersonResponse findById(@PathVariable("id") UUID id) {
        return PersonMapper.toPersonResponse(getUseCase.findById(id));
    }

}
