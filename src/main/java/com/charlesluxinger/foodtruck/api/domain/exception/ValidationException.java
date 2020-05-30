package com.charlesluxinger.foodtruck.api.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.BindingResult;

@AllArgsConstructor
@Getter
public class ValidationException extends RuntimeException {
    private BindingResult bindingResult;
}
