package com.charlesluxinger.foodtruck.api.domain.exception.handler;

import com.charlesluxinger.foodtruck.api.domain.exception.ConstraintEntityViolationException;
import com.charlesluxinger.foodtruck.api.domain.exception.DomainException;
import com.charlesluxinger.foodtruck.api.domain.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice()
public class ApiExceptionHandler {

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<?> httpMediaTypeNotSupportedExceptionHandler(){
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                             .body(ExceptionResponse.builder()
                                                    .message("Media Type Supported: application/json")
                                                    .build()
                             );
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> entityNotFoundExceptionHandler(EntityNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(ExceptionResponse.builder()
                                                    .message(e.getMessage())
                                                    .build()
                             );
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<?> domainExceptionHandler(DomainException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(ExceptionResponse.builder()
                                    .message(e.getMessage())
                                    .build()
                             );
    }

    @ExceptionHandler(ConstraintEntityViolationException.class)
    public ResponseEntity<?> constraintEntityViolationExceptionHandler(ConstraintEntityViolationException e){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ExceptionResponse.builder()
                        .message(e.getMessage())
                        .build()
                );
    }

}