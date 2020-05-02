package com.charlesluxinger.foodtruck.api.domain.model;

import javax.persistence.Embeddable;

@Embeddable
public enum StatusPedido {

    CRIADO,
    CONFIRMADO,
    ENTREGUE,
    CANCELADO

}
