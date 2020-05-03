package com.charlesluxinger.foodtruck.api.domain.exception;

public class EntityNotFoundException extends DomainException {

    private static final long serialVersionUID = 1L;

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}
