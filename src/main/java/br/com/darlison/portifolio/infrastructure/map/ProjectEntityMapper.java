package br.com.darlison.portifolio.infrastructure.map;

import br.com.darlison.portifolio.domain.model.Person;
import br.com.darlison.portifolio.domain.model.Project;
import br.com.darlison.portifolio.infrastructure.database.entity.PersonEntity;
import br.com.darlison.portifolio.infrastructure.database.entity.ProjectEntity;

import java.util.stream.Collectors;

public class ProjectEntityMapper {

    public static ProjectEntity toProjectEntity(Project project) {
        final ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(project.getId());
        projectEntity.setCreatedAt(project.getCreatedAt());
        projectEntity.setUpdatedAt(project.getUpdatedAt());
        projectEntity.setName(project.getName());
        projectEntity.setDescription(project.getDescription());
        projectEntity.setBeginDate(project.getBeginDate());
        projectEntity.setEndDate(project.getEndDate());
        projectEntity.setExpectedEndDate(project.getExpectedEndDate());
        projectEntity.setBudget(project.getBudget());
        projectEntity.setRisk(project.getRisk());
        projectEntity.setStatus(project.getStatus());
        projectEntity.setManagerEntity(new PersonEntity(project.getManager().getId()));
        return projectEntity;
    }

    public static Project toProject(ProjectEntity projectEntity) {
        final Project project = new Project();
        project.setId(projectEntity.getId());
        project.setCreatedAt(projectEntity.getCreatedAt());
        project.setUpdatedAt(projectEntity.getUpdatedAt());
        project.setName(projectEntity.getName());
        project.setDescription(projectEntity.getDescription());
        project.setBudget(projectEntity.getBudget());
        project.setStatus(projectEntity.getStatus());
        project.setRisk(projectEntity.getRisk());
        project.setBeginDate(projectEntity.getBeginDate());
        project.setEndDate(projectEntity.getEndDate());
        project.setExpectedEndDate(projectEntity.getExpectedEndDate());
        project.setManager(PersonEntityMapper.toPerson(projectEntity.getManagerEntity()));
        project.setMembers(
            projectEntity.getMemberList()
                         .stream()
                         .map(it -> PersonEntityMapper.toPerson(it.getPersonEntity()))
                         .collect(Collectors.toList())
        );
        return project;
    }

}
