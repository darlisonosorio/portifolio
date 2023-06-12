package br.com.darlison.portifolio.domain.exceptions;

import br.com.darlison.portifolio.common.exception.BadRequestException;

public class ProjectNotFoundException extends BadRequestException {

    public ProjectNotFoundException() {
        super("EXC-PROJ-NF", "Projeto não encontrado.");
    }

}
