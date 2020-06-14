package com.charlesluxinger.foodtruck.api.domain.entity;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum StatusPedido {
    CRIADO("Criado"),
    CONFIRMADO("Confirmado", CRIADO),
    ENTREGUE("Entregue", CONFIRMADO),
    CANCELADO("Confirmado", CRIADO);

    StatusPedido(String descricao, StatusPedido... statusAnterior) {
        this.descricao = descricao;
        this.statusAnterior = Arrays.asList((statusAnterior));
    }

    private String descricao;
    private List<StatusPedido> statusAnterior;

    public boolean naoPodeMudarStatusPara(StatusPedido novoStatus){
        return !novoStatus.statusAnterior.contains(this);
    }

}
