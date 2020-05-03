package com.charlesluxinger.foodtruck.api.domain.exception;

public class CidadeNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public CidadeNotFoundException(long id, Throwable cause) {
        super(String.format("Cidade de ID: %d não encontrada.", id), cause);
    }

    public CidadeNotFoundException(Long id) {
        super(String.format("Cidade de ID: %d não encontrada.", id));
    }
}
