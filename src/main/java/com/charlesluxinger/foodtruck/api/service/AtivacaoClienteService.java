package com.charlesluxinger.foodtruck.api.service;

import org.springframework.stereotype.Component;

import com.charlesluxinger.foodtruck.api.model.Cliente;
import com.charlesluxinger.foodtruck.api.notification.Notificador;

@Component
public class AtivacaoClienteService {

	// @Autowired(required = false) - Pontos de Injeção
	// Dependencia opcional
	private Notificador notificador;

	// @Autowired - Pontos de Injeção
	// Quando há apenas 1 construtor a anotacao nao e necessaria
	public AtivacaoClienteService(Notificador notificador) {
		this.setNotificador(notificador);
	}

	public void ativar(Cliente cliente) {
		cliente.ativar();

		if (notificador != null) {
			this.notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");
		}
	}

	// @Autowired - Pontos de Injeção
	public void setNotificador(Notificador notificador) {
		this.notificador = notificador;
	}

}
