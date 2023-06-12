package br.com.darlison.portifolio.application.rest;

import br.com.darlison.portifolio.application.config.swagger.annotiations.CommonResponses;
import br.com.darlison.portifolio.application.map.ProjectMapper;
import br.com.darlison.portifolio.application.model.input.ProjectRequest;
import br.com.darlison.portifolio.application.model.output.ProjectResponse;
import br.com.darlison.portifolio.domain.model.Page;
import br.com.darlison.portifolio.domain.model.Project;
import br.com.darlison.portifolio.domain.port.usecase.project.GetProjectUseCase;
import br.com.darlison.portifolio.domain.port.usecase.project.SaveProjectUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;
import java.util.stream.Collectors;

@Tag(name = "Endpoint de Projetos")
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/v1/project")
public class ProjectController {

    private final SaveProjectUseCase saveUseCase;
    private final GetProjectUseCase getUseCase;

    @PostMapping
    @CommonResponses
    @Operation(
            method = "POST",
            description = "Requisição POST para salvar Projeto",
            summary = "POST cadastrar Projeto"
    )
    public void create(@RequestBody() @Valid final ProjectRequest request) {
        saveUseCase.save(ProjectMapper.toProject(request));
    }


    @GetMapping
    @CommonResponses
    @Operation(
            method = "GET",
            description = "Requisição GET para obter a lista de Projetos",
            summary = "GET listar Projetos"
    )
    public Page<ProjectResponse> list(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit
    ) {
        Page<Project> result = getUseCase.findAll(name, page, limit);
        return new Page<>(
            result.getContent().stream()
                  .map(ProjectMapper::toProjectResponse).collect(Collectors.toList()),
            result.getTotalElements()
        );
    }

    @GetMapping("/{id}")
    @CommonResponses
    @Operation(
            method = "GET",
            description = "Requisição GET para um Projeto",
            summary = "GET obter Projeto"
    )
    public ProjectResponse findById(@PathVariable("id") UUID id) {
        return ProjectMapper.toProjectResponse(getUseCase.findById(id));
    }

}
