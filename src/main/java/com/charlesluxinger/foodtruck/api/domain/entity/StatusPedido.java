package com.charlesluxinger.foodtruck.api.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Embeddable;

@Getter
@Embeddable
@AllArgsConstructor
public enum StatusPedido {

    CRIADO("Criado"),
    CONFIRMADO("Confirmado"),
    ENTREGUE("Entregue"),
    CANCELADO("Confirmado");

    private String descricao;

}
