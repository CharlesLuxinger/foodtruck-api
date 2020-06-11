package com.charlesluxinger.foodtruck.api.domain.exception;

public class FormaPagamentoNotFoundException extends EntityNotFoundException{

	public FormaPagamentoNotFoundException(String mensagem) {
		super(mensagem);
	}

	public FormaPagamentoNotFoundException(Long estadoId) {
		this(String.format("Não existe um cadastro de forma de pagamento com código %d", estadoId));
	}
}
