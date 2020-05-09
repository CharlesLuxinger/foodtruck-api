package com.charlesluxinger.foodtruck.api.domain.exception.handler;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Getter
@Builder(access = AccessLevel.PACKAGE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionResponse {

    private Integer status;
    private String type;
    private String title;
    private String detail;
    private final LocalDateTime timestamp = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);

}
