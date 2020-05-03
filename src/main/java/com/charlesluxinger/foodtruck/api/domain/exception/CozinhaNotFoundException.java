package com.charlesluxinger.foodtruck.api.domain.exception;

public class CozinhaNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public CozinhaNotFoundException(long id, Throwable cause) {
        super(String.format("Cozinha de ID: %d não encontrada.", id), cause);
    }

    public CozinhaNotFoundException(Long id) {
        super(String.format("Cozinha de ID: %d não encontrada.", id));
    }
}
