package com.charlesluxinger.foodtruck.api.domain.exception;

public class GrupoNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public GrupoNotFoundException(long id, Throwable cause) {
        super(String.format("Grupo de ID: %d não encontrada.", id), cause);
    }

    public GrupoNotFoundException(Long id) {
        super(String.format("Grupo de ID: %d não encontrada.", id));
    }
}
