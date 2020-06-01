package com.charlesluxinger.foodtruck.api.jackson;

import com.charlesluxinger.foodtruck.api.domain.model.Restaurante;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public abstract class CozinhaMixin {

    @JsonIgnore
    private List<Restaurante> restaurantes;

}