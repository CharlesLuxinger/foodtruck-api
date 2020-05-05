package com.charlesluxinger.foodtruck.api.domain.exception.handler;

import com.charlesluxinger.foodtruck.api.domain.exception.ConstraintEntityViolationException;
import com.charlesluxinger.foodtruck.api.domain.exception.DomainException;
import com.charlesluxinger.foodtruck.api.domain.exception.EntityNotFoundException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice()
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request){
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<?> handleDomainException(DomainException ex, WebRequest request){
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(ConstraintEntityViolationException.class)
    public ResponseEntity<?> handleConstraintEntityViolationException(ConstraintEntityViolationException ex, WebRequest request){
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (body instanceof String){
            body = ExceptionResponse.builder()
                                    .title(status.getReasonPhrase())
                                    .status(status.value())
                                    .detail((String) body)
                                    .build();
        } else if (body == null){
            body = ExceptionResponse.builder()
                                    .status(status.value())
                                    .title(status.getReasonPhrase())
                                    .build();
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Throwable rootCause = ExceptionUtils.getRootCause(ex);
        
        if (rootCause instanceof InvalidFormatException){
            return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
        }

        ExceptionResponse response = ExceptionResponse.builder()
                                                      .detail("Body request invalid, check the message sent.")
                                                      .status(status.value())
                                                      .title(status.getReasonPhrase())
                                                      .build();

        return handleExceptionInternal(ex, response, new HttpHeaders(), status, request);
    }

    private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String path = ex.getPath().stream()
                                  .map(ref -> ref.getFieldName())
                                  .collect(Collectors.joining("."));

        String detail = String.format("Property '%s' with value '%s' is incompatible. Correct type is: %s", path, ex.getValue(), ex.getTargetType().getSimpleName());


        ExceptionResponse response = ExceptionResponse.builder()
                                                      .detail(detail)
                                                      .status(status.value())
                                                      .title(status.getReasonPhrase())
                                                      .build();

        return handleExceptionInternal(ex, response, new HttpHeaders(), status, request);
    }

}