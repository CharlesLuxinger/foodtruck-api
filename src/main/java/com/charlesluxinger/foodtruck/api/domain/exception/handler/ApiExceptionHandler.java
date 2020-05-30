package com.charlesluxinger.foodtruck.api.domain.exception.handler;

import com.charlesluxinger.foodtruck.api.domain.exception.ConstraintEntityViolationException;
import com.charlesluxinger.foodtruck.api.domain.exception.DomainException;
import com.charlesluxinger.foodtruck.api.domain.exception.EntityNotFoundException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.constraints.NotNull;
import java.util.List;
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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        String detail = "An unexpected internal system error has occurred. Try again and if the problem persists, contact your system administrator.";

        ex.printStackTrace();

        return handleExceptionInternal(ex, exceptionResponseBuilder(status, detail), new HttpHeaders(), status, request);
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
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (ex instanceof MethodArgumentTypeMismatchException){
            return handleMethodArgumentTypeMismatchException((MethodArgumentTypeMismatchException)ex, headers, status, request);
        }


        return super.handleTypeMismatch(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Throwable rootCause = ExceptionUtils.getRootCause(ex);

        if (rootCause instanceof InvalidFormatException){
            return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
        } else if (rootCause instanceof PropertyBindingException){
            return handlePropertyBindingException((PropertyBindingException) rootCause, headers, status, request);
        }

        ExceptionResponse response = exceptionResponseBuilder(status, "Body request invalid, check the message sent.");

        return handleExceptionInternal(ex, response, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
                                                                   HttpHeaders headers, HttpStatus status, WebRequest request) {

        String detail = String.format("The %s resource, which you tried to access, does not exist.",
                ex.getRequestURL());

        return handleExceptionInternal(ex, exceptionResponseBuilder(status, detail), headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        String detail = "One or more fields are invalid. Fill in correctly and try again.";

        BindingResult bindingResult = ex.getBindingResult();

        List<ExceptionResponse.Field> fields = bindingResult.getFieldErrors()
                                                            .stream()
                                                            .map(fieldError -> ExceptionResponse.Field
                                                                    .builder()
                                                                    .name(fieldError.getField())
                                                                    .userMessage(fieldError.getDefaultMessage())
                                                                    .build())
                                                            .collect(Collectors.toList());

        return handleExceptionInternal(ex, exceptionResponseBuilder(status, detail, fields), headers, status, request);
    }

    private ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String detail = String.format("The URL parameter '%s' was given the value '%s', which is of an invalid type. Correct and enter a value compatible with type %s.",
                ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());

        return handleExceptionInternal(ex, exceptionResponseBuilder(status, detail), headers, status, request);
    }

    private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String path = joinPath(ex.getPath());

        String detail = String.format("Property '%s' with value '%s' is incompatible. Correct type is: %s", path, ex.getValue(), ex.getTargetType().getSimpleName());

        return handleExceptionInternal(ex, exceptionResponseBuilder(status, detail), new HttpHeaders(), status, request);

    }

    private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request){

        String path = joinPath(ex.getPath());

        String detail = String.format("Property '%s' not found. It is not correct or remove the property", path);

        return handleExceptionInternal(ex, exceptionResponseBuilder(status, detail), new HttpHeaders(), status, request);

    }

    private ExceptionResponse exceptionResponseBuilder(HttpStatus status, @NotNull String detail) {
        return exceptionResponseBuilder(status, detail, null);
    }

    private ExceptionResponse exceptionResponseBuilder(HttpStatus status, String detail, List<ExceptionResponse.Field> fields) {
        return ExceptionResponse.builder()
                .detail(detail)
                .status(status.value())
                .title(status.getReasonPhrase())
                .fields(fields)
                .build();
    }

    private String joinPath(List<JsonMappingException.Reference> path) {
        return path.stream()
                   .map(ref -> ref.getFieldName())
                   .collect(Collectors.joining("."));
    }

}