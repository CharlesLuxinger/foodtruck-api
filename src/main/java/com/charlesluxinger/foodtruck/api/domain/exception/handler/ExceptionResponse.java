package com.charlesluxinger.foodtruck.api.domain.exception.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PACKAGE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionResponse {

    private Integer status;
    private String type;
    private String title;
    private String detail;

}
