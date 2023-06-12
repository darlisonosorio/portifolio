package br.com.darlison.portifolio.domain.adapters.project;

import br.com.darlison.portifolio.domain.exceptions.ProjectNotFoundException;
import br.com.darlison.portifolio.domain.model.Project;
import br.com.darlison.portifolio.domain.port.repository.ProjectRepository;
import br.com.darlison.portifolio.domain.port.usecase.project.SaveProjectUseCase;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SaveProjectUseCaseImpl implements SaveProjectUseCase {

    private final ProjectRepository projectRepository;

    public SaveProjectUseCaseImpl(final ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public void save(final Project project) {
        Project toSave = Objects.isNull(project.getId())
                ? new Project()
                : projectRepository.findById(project.getId())
                .orElseThrow(ProjectNotFoundException::new);

        toSave.setBeginDate(project.getBeginDate());
        toSave.setExpectedEndDate(project.getExpectedEndDate());
        toSave.setEndDate(project.getEndDate());
        toSave.setBudget(project.getBudget());
        toSave.setName(project.getName());
        toSave.setDescription(project.getDescription());
        toSave.setRisk(project.getRisk());
        toSave.setStatus(project.getStatus());
        toSave.setManager(project.getManager());
        toSave.setMembers(project.getMembers());

        projectRepository.save(toSave);
    }
}
