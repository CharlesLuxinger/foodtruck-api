package com.charlesluxinger.foodtruck.api.domain.exception;

public class RestauranteNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public RestauranteNotFoundException(long id, Throwable cause) {
        super(String.format("Restaurante de ID: %d não encontrada.", id), cause);
    }

    public RestauranteNotFoundException(Long id) {
        super(String.format("Restaurante de ID: %d não encontrada.", id));
    }
}
