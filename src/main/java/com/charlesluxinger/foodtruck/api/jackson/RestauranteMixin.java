package com.charlesluxinger.foodtruck.api.jackson;

import com.charlesluxinger.foodtruck.api.domain.model.Cozinha;
import com.charlesluxinger.foodtruck.api.domain.model.Endereco;
import com.charlesluxinger.foodtruck.api.domain.model.FormaPagamento;
import com.charlesluxinger.foodtruck.api.domain.model.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class RestauranteMixin {

    @JsonIgnoreProperties(value = "nome", allowGetters = true)
    private Cozinha cozinha;

    @JsonIgnore
    private Endereco endereco;

    @JsonIgnore
    private List<FormaPagamento> formasPagamento = new ArrayList<>();

    @JsonIgnore
    private OffsetDateTime dataCadastro;

    @JsonIgnore
    private OffsetDateTime dataAtualizacao;

    @JsonIgnore
    List<Produto> produtos = new ArrayList<>();
}

