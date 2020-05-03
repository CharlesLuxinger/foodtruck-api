package com.charlesluxinger.foodtruck.api.domain.exception.handler;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder(access = AccessLevel.PACKAGE)
public class ExceptionResponse {

    private final LocalDateTime dateTime = LocalDateTime.now();

    private String message;
}
