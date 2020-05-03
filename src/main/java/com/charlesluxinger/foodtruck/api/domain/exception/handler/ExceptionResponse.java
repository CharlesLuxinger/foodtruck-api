package com.charlesluxinger.foodtruck.api.domain.exception.handler;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder(access = AccessLevel.PACKAGE)
public class ExceptionResponse {

    @JsonProperty(value = "date_time")
    private final LocalDateTime dateTime = LocalDateTime.now();

    private String message;
}
