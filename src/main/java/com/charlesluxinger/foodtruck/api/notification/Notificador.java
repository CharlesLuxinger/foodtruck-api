package com.charlesluxinger.foodtruck.api.notification;

import com.charlesluxinger.foodtruck.api.model.Cliente;

public interface Notificador {

	void notificar(Cliente cliente, String mensagem);

}
