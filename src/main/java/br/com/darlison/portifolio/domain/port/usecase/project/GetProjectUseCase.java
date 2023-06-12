package br.com.darlison.portifolio.domain.port.usecase.project;

import br.com.darlison.portifolio.domain.model.Page;
import br.com.darlison.portifolio.domain.model.Project;

import java.util.UUID;

public interface GetProjectUseCase {

    Project findById(UUID id);

    Page<Project> findAll(String description, int page, int limit);

}
