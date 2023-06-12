package br.com.darlison.portifolio.domain.exceptions;

import br.com.darlison.portifolio.common.exception.BadRequestException;

public class PersonNotFoundException extends BadRequestException {

    public PersonNotFoundException() {
        super("EXC-PESS-NF", "Pessoa não encontrada.");
    }

}
