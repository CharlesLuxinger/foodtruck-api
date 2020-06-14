package com.charlesluxinger.foodtruck.api.domain.exception;

public class PermissaoNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public PermissaoNotFoundException(Long permissaoId) {
        super(String.format("Não existe um cadastro de permissão com código %d", permissaoId));
    }

}