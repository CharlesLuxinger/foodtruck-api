package com.charlesluxinger.foodtruck.api.domain.exception;

import javax.validation.constraints.NotNull;

public class ConstraintEntityViolationException extends DomainException {

    private static final long serialVersionUID = 1L;

    public ConstraintEntityViolationException(@NotNull Class<?> tClass, Long id) {
        super(String.format("Entidade %s de ID: %d não pode ser removida, pois está em uso.", tClass.getSimpleName(), id));
    }
}
