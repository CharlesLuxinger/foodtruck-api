package com.charlesluxinger.foodtruck.api.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotNull;

public class EntityNotFoundException extends ResponseStatusException {

    private static final long serialVersionUID = 1L;

    public EntityNotFoundException(@NotNull Class<?> tClass, long id) {
        super(HttpStatus.NOT_FOUND, String.format("Entidade %s de ID: %d não encontrada.", tClass.getName(), id));
    }
}
