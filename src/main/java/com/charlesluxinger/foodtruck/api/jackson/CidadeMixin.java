package com.charlesluxinger.foodtruck.api.jackson;

import com.charlesluxinger.foodtruck.api.domain.model.Estado;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public abstract class CidadeMixin {

    @JsonIgnoreProperties(value = "nome", allowGetters = true)
    private Estado estado;

}