package com.charlesluxinger.foodtruck.api.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EntityNotFoundException extends ResponseStatusException {

    private static final long serialVersionUID = 1L;

    public EntityNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
