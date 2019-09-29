package com.charlesluxinger.foodtruck.api.notification;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.charlesluxinger.foodtruck.api.model.Cliente;

//@Primary
@Component
@TipoNotificador(NivelUrgencia.NORMAL)
public class NotificadorSMS implements Notificador, InitializingBean, DisposableBean {

	@Override
	public void notificar(Cliente cliente, String mensagem) {
		System.out.printf("Notificando %s por SMS através do telefone %s: %s\n",
				cliente.getNome(), cliente.getTelefone(), mensagem);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("Init");

	}

	@Override
	public void destroy() throws Exception {
		System.out.println("Destroy");
	}

}
