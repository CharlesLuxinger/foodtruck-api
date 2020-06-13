package com.charlesluxinger.foodtruck.api.domain.exception;

public class ProdutoNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public ProdutoNotFoundException(String message){
        super(message);
    }

    public ProdutoNotFoundException (Long permissaoId) {
        this(String.format("Não existe um cadastro de permissão com código %d", permissaoId));
    }

}