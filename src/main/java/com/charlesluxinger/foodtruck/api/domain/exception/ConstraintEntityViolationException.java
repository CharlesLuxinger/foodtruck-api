package com.charlesluxinger.foodtruck.api.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ConstraintEntityViolationException extends ResponseStatusException {

    private static final long serialVersionUID = 1L;

    public ConstraintEntityViolationException(String message) {
        super(HttpStatus.CONFLICT, message);
    }
}
