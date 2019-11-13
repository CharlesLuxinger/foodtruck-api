package com.charlesluxinger.foodtruck.api.domain.exception;

public class ConstraintEntityViolationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ConstraintEntityViolationException(String message) {
        super(message);
    }
}
