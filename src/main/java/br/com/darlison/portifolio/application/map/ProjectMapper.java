package br.com.darlison.portifolio.application.map;

import br.com.darlison.portifolio.application.model.input.ProjectRequest;
import br.com.darlison.portifolio.application.model.output.ProjectResponse;
import br.com.darlison.portifolio.domain.model.Person;
import br.com.darlison.portifolio.domain.model.Project;

import java.util.stream.Collectors;

public class ProjectMapper {

    public static Project toProject(ProjectRequest projectRequest) {
        final Project project = new Project();
        project.setName(projectRequest.getName());
        project.setDescription(projectRequest.getDescription());
        project.setBeginDate(projectRequest.getBeginDate());
        project.setEndDate(projectRequest.getEndDate());
        project.setExpectedEndDate(projectRequest.getExpectedEndDate());
        project.setBudget(projectRequest.getBudget());
        project.setStatus(projectRequest.getStatus());
        project.setRisk(projectRequest.getRisk());
        project.setManager(new Person(projectRequest.getManager()));
        project.setMembers(projectRequest.getMembers().stream().map(Person::new).collect(Collectors.toList()));
        return project;
    }

    public static ProjectResponse toProjectResponse(Project project) {
        final ProjectResponse response = new ProjectResponse();
        response.setId(project.getId());
        response.setCreatedAt(project.getCreatedAt());
        response.setUpdatedAt(project.getUpdatedAt());
        response.setName(project.getName());
        response.setDescription(project.getDescription());
        response.setBudget(project.getBudget());
        response.setRisk(project.getRisk());
        response.setStatus(project.getStatus());
        response.setBeginDate(project.getBeginDate());
        response.setEndDate(project.getEndDate());
        response.setExpectedEndDate(project.getExpectedEndDate());
        response.setManager(PersonMapper.toPersonResponse(project.getManager()));
        response.setMembers(
            project.getMembers()
                   .stream()
                   .map(PersonMapper::toPersonResponse)
                   .collect(Collectors.toList())
        );
        return response;
    }
}
