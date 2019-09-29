package com.charlesluxinger.foodtruck.api.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.charlesluxinger.foodtruck.api.notification.NivelUrgencia;
import com.charlesluxinger.foodtruck.api.notification.Notificador;
import com.charlesluxinger.foodtruck.api.notification.TipoNotificador;
import com.charlesluxinger.foodtruck.api.service.CLienteAtivadoEvent;

@Component
public class NotificacaoService {

	@Autowired
	@TipoNotificador(value = NivelUrgencia.NORMAL)
	private Notificador notificador;

	@EventListener
	public void clienteAtivadoLister(CLienteAtivadoEvent event) {
		System.out.println(event.getCliente().getNome() + " ativado!");
		notificador.notificar(event.getCliente(), "Seu cadastro no sistema está ativo!");
	}
}
