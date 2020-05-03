package com.charlesluxinger.foodtruck.api.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotNull;

public class ConstraintEntityViolationException extends ResponseStatusException {

    private static final long serialVersionUID = 1L;

    public ConstraintEntityViolationException(@NotNull Class<?> tClass, Long id) {
        super(HttpStatus.CONFLICT, String.format("Entidade %s de ID: %d não pode ser removida, pois está em uso.", tClass.getName(), id));
    }
}
