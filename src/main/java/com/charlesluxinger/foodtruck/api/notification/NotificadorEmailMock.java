package com.charlesluxinger.foodtruck.api.notification;

import org.springframework.context.annotation.Profile;

import com.charlesluxinger.foodtruck.api.model.Cliente;

@Profile("DEV")
public class NotificadorEmailMock implements Notificador {

	private boolean upperCase;
	private String hostServerSmtp;

	public NotificadorEmailMock(String hostServerSmtp) {
		this.hostServerSmtp = hostServerSmtp;
	}

	@Override
	public void notificar(Cliente cliente, String mensagem) {

		if (upperCase) {
			mensagem = mensagem.toUpperCase();
		}

		System.out.printf("Mock: Notificando %s através do e-mail %s: %s, Server: %s\n", cliente.getNome(),
				cliente.getEmail(), mensagem, hostServerSmtp);
	}

	public void setUpperCase(boolean upperCase) {
		this.upperCase = upperCase;
	}

	public void init() {
		System.out.println("Init");

	}

	public void destroy() {
		System.out.println("Destroy");

	}

}
