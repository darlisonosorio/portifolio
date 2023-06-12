package br.com.darlison.portifolio.infrastructure.service;

import br.com.darlison.portifolio.domain.model.Page;
import br.com.darlison.portifolio.domain.model.Project;
import br.com.darlison.portifolio.domain.port.repository.ProjectRepository;
import br.com.darlison.portifolio.infrastructure.database.entity.PersonEntity;
import br.com.darlison.portifolio.infrastructure.database.entity.PersonProjectEntity;
import br.com.darlison.portifolio.infrastructure.database.entity.ProjectEntity;
import br.com.darlison.portifolio.infrastructure.database.repository.ProjectDatabaseRepository;
import br.com.darlison.portifolio.infrastructure.map.ProjectEntityMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService implements ProjectRepository {

    private final ProjectDatabaseRepository databaseRepository;
    private final ObjectMapper objectMapper;

    @Override
    public UUID save(final Project project) {
        final ProjectEntity entity = ProjectEntityMapper.toProjectEntity(project);
        ProjectEntity saved = databaseRepository.save(entity);
        if (saved.getMemberList() == null) saved.setMemberList(new ArrayList<>());
        saved.getMemberList().clear();
        saved.getMemberList().addAll(
                project.getMembers()
                        .stream()
                        .map(it -> new PersonProjectEntity(new PersonEntity(it.getId()), new ProjectEntity(saved.getId())))
                        .collect(Collectors.toList())
        );
        return databaseRepository.save(saved).getId();
    }

    @Override
    public Page<Project> findAll(final String description, final int page, final int limit) {
        org.springframework.data.domain.Page<ProjectEntity> result =
                databaseRepository.findAllByName(description, PageRequest.of(page, limit));
        return new Page<>(
                result.getContent().stream()
                        .map(ProjectEntityMapper::toProject)
                        .collect(Collectors.toList()),
                result.getTotalElements()
        );
    }

    @Override
    public Optional<Project> findById(UUID id) {
        return databaseRepository.findById(id).map(ProjectEntityMapper::toProject);
    }
}
