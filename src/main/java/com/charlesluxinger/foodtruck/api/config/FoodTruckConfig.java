package com.charlesluxinger.foodtruck.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.charlesluxinger.foodtruck.api.notification.NotificadorEmail;

@Configuration
public class FoodTruckConfig {

	@Bean
	public NotificadorEmail notificadorEmail() {
		NotificadorEmail notificador = new NotificadorEmail("smtpserver.foodtruck-api.com.br");
		notificador.setUpperCase(true);

		return notificador;
	}

}
