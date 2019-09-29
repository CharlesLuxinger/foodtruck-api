package com.charlesluxinger.foodtruck.api.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.charlesluxinger.foodtruck.api.model.Cliente;

@Component
public class AtivacaoClienteService {

	// @Autowired(required = false) - Pontos de Injeção
	// Dependencia opcional
	// @Autowired
	// @Qualifier("sms")
	// @TipoNotificador(value = NivelUrgencia.NORMAL)
	// private List<Notificador> notificadores;

	// @Autowired - Pontos de Injeção
	// Quando há apenas 1 construtor a anotacao nao e necessaria
	//	public AtivacaoClienteService(Notificador notificador) {
	//		this.setNotificador(notificador);
	//	}

	@Autowired
	private ApplicationEventPublisher eventPublisher;

	public void ativar(Cliente cliente) {
		cliente.ativar();


		// if (notificadores != null) {
		// this.notificadores.forEach(x -> x.notificar(cliente, "Seu cadastro no sistema
		// está ativo!"));
		// }

		eventPublisher.publishEvent(new CLienteAtivadoEvent(cliente));
	}

	// @Autowired - Pontos de Injeção
	// public void setNotificador(Notificador notificador) {
	//		this.notificador = notificador;
	// }

	@PostConstruct
	public void init() {
		System.out.println("Init");

	}

	@PreDestroy
	public void destroy() {
		System.out.println("Destroy");

	}
}
