package com.charlesluxinger.foodtruck.api.domain.repository;

import com.charlesluxinger.foodtruck.api.domain.entity.Restaurante;

import java.math.BigDecimal;
import java.util.List;

public interface RestauranteRepositoryQueries {

    public List<Restaurante> find(String nome, BigDecimal taxaFreteInit, BigDecimal taxaFreteFinal);

}
