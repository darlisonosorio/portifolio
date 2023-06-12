package br.com.darlison.portifolio.domain.port.repository;

import br.com.darlison.portifolio.domain.model.Page;
import br.com.darlison.portifolio.domain.model.Project;

import java.util.Optional;
import java.util.UUID;

public interface ProjectRepository {

    UUID save(Project project);

    Page<Project> findAll(String description, int page, int limit);

    Optional<Project> findById(UUID id);

}
