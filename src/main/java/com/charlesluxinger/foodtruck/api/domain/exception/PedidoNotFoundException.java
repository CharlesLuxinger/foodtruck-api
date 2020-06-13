package com.charlesluxinger.foodtruck.api.domain.exception;

public class PedidoNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public PedidoNotFoundException(String mensagem) {
        super(mensagem);
    }

    public PedidoNotFoundException(Long pedidoId) {
        this(String.format("Não existe um pedido com código %d", pedidoId));
    }
}
