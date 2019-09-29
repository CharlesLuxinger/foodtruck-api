package com.charlesluxinger.foodtruck.api.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;

import com.charlesluxinger.foodtruck.api.model.Cliente;

@Profile("PROD")
public class NotificadorEmail implements Notificador {

	private boolean upperCase;

	// @Value("${noticador.email.server-host}")
	// private String hostServerSmtp;

	// @Value("${noticador.email.server-port}")
	// private Integer port;

	@Autowired
	private NotificadorProperties properties;

	//	public NotificadorEmail(String hostServerSmtp) {
	//		this.hostServerSmtp = hostServerSmtp;
	//	}

	@Override
	public void notificar(Cliente cliente, String mensagem) {

		if (upperCase) {
			mensagem = mensagem.toUpperCase();
		}

		System.out.printf("Notificando %s através do e-mail %s: %s, Server: %s, Port: %s\n", cliente.getNome(),
				cliente.getEmail(), mensagem, properties.getServerHost(), properties.getServerPort());
	}

	public void setUpperCase(boolean upperCase) {
		this.upperCase = upperCase;
	}

}
