package com.charlesluxinger.foodtruck.api.domain.exception;

public class PedidoNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public PedidoNotFoundException(String codigo) {
        super(String.format("Não existe um pedido com código %d", codigo));
    }
}
