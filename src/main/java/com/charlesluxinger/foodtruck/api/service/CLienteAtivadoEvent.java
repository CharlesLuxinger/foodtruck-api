package com.charlesluxinger.foodtruck.api.service;

import com.charlesluxinger.foodtruck.api.model.Cliente;

public class CLienteAtivadoEvent {
	private Cliente cliente;

	public CLienteAtivadoEvent(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cliente getCliente() {
		return cliente;
	}
}
