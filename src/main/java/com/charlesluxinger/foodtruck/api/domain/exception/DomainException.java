package com.charlesluxinger.foodtruck.api.domain.exception;

public class DomainException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DomainException(String message, Throwable e) {
        super(message, e);
    }

    public DomainException(String message) {
        super(message);
    }
}
