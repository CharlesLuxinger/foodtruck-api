package com.charlesluxinger.foodtruck.api.domain.exception;

public class EstadoNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public EstadoNotFoundException(long id, Throwable cause) {
        super(String.format("Estado de ID: %d não encontrada.", id), cause);
    }

    public EstadoNotFoundException(Long id) {
        super(String.format("Estado de ID: %d não encontrada.", id));
    }
}
