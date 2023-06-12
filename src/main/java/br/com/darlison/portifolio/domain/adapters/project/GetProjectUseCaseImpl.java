package br.com.darlison.portifolio.domain.adapters.project;

import br.com.darlison.portifolio.domain.exceptions.ProjectNotFoundException;
import br.com.darlison.portifolio.domain.model.Page;
import br.com.darlison.portifolio.domain.model.Project;
import br.com.darlison.portifolio.domain.port.repository.ProjectRepository;
import br.com.darlison.portifolio.domain.port.usecase.project.GetProjectUseCase;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetProjectUseCaseImpl implements GetProjectUseCase {

    private final ProjectRepository projectRepository;

    public GetProjectUseCaseImpl(final ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project findById(final UUID id) {
        return projectRepository.findById(id).orElseThrow(ProjectNotFoundException::new);
    }

    @Override
    public Page<Project> findAll(final String description, final int page, final int limit) {
        return projectRepository.findAll(description, page, limit);
    }
}
