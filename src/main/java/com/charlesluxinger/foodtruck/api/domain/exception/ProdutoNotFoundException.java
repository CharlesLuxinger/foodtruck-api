package com.charlesluxinger.foodtruck.api.domain.exception;

public class ProdutoNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public ProdutoNotFoundException(String message){
        super(message);
    }

    public ProdutoNotFoundException(Long restauranteId, Long produtoId) {
        super(String.format("Não existe um cadastro de produto com código %d para o restaurante de código %d",
                produtoId, restauranteId));
    }

}