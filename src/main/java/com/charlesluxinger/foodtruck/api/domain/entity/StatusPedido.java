package com.charlesluxinger.foodtruck.api.domain.entity;

import javax.persistence.Embeddable;

@Embeddable
public enum StatusPedido {

    CRIADO,
    CONFIRMADO,
    ENTREGUE,
    CANCELADO

}
