package com.charlesluxinger.foodtruck.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.charlesluxinger.foodtruck.api.model.Cliente;
import com.charlesluxinger.foodtruck.api.notification.NivelUrgencia;
import com.charlesluxinger.foodtruck.api.notification.Notificador;
import com.charlesluxinger.foodtruck.api.notification.TipoNotificador;

@Component
public class AtivacaoClienteService {

	// @Autowired(required = false) - Pontos de Injeção
	// Dependencia opcional
	@Autowired
	// @Qualifier("sms")
	@TipoNotificador(value = NivelUrgencia.NORMAL)
	private List<Notificador> notificadores;

	// @Autowired - Pontos de Injeção
	// Quando há apenas 1 construtor a anotacao nao e necessaria
	//	public AtivacaoClienteService(Notificador notificador) {
	//		this.setNotificador(notificador);
	//	}

	public void ativar(Cliente cliente) {
		cliente.ativar();

		if (notificadores != null) {
			this.notificadores.forEach(x -> x.notificar(cliente, "Seu cadastro no sistema está ativo!"));
		}
	}

	// @Autowired - Pontos de Injeção
	// public void setNotificador(Notificador notificador) {
	//		this.notificador = notificador;
	// }

}
