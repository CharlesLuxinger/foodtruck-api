package com.charlesluxinger.foodtruck.api.notification;

import com.charlesluxinger.foodtruck.api.model.Cliente;

public class NotificadorEmail implements Notificador {

	private boolean upperCase;
	private String hostServerSmtp;

	public NotificadorEmail(String hostServerSmtp) {
		this.hostServerSmtp = hostServerSmtp;
	}

	@Override
	public void notificar(Cliente cliente, String mensagem) {

		if (upperCase) {
			mensagem = mensagem.toUpperCase();
		}

		System.out.printf("Notificando %s através do e-mail %s: %s, Server: %s\n", cliente.getNome(),
				cliente.getEmail(), mensagem, hostServerSmtp);
	}

	public void setUpperCase(boolean upperCase) {
		this.upperCase = upperCase;
	}

}
