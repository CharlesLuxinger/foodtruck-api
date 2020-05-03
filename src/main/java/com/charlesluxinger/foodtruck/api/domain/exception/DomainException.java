package com.charlesluxinger.foodtruck.api.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DomainException extends ResponseStatusException {

    private static final long serialVersionUID = 1L;

    public DomainException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
