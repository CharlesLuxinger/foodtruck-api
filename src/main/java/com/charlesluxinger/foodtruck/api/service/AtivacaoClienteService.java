package com.charlesluxinger.foodtruck.api.service;

import org.springframework.stereotype.Component;

import com.charlesluxinger.foodtruck.api.model.Cliente;
import com.charlesluxinger.foodtruck.api.notification.Notificador;

@Component
public class AtivacaoClienteService {

	private Notificador notificador;

	public AtivacaoClienteService(Notificador notificador) {
		this.notificador = notificador;
	}

	public void ativar(Cliente cliente) {
		cliente.ativar();

		this.notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");
	}

}
