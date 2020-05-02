package com.charlesluxinger.foodtruck.api.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ConstraintEntityViolationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ConstraintEntityViolationException(String message) {
        super(message);
    }
}
