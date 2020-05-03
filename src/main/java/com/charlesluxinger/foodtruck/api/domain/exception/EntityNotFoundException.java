package com.charlesluxinger.foodtruck.api.domain.exception;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends DomainException {

    private static final long serialVersionUID = 1L;

    public EntityNotFoundException(String message, Throwable cause) {
        super(HttpStatus.NOT_FOUND, message, cause);
    }

    public EntityNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
