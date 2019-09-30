package com.charlesluxinger.foodtruck.api.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.charlesluxinger.foodtruck.api.FoodtruckApiApplication;

public class TesteJPA {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(FoodtruckApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);

		CadastroCozinha cadastroCozinha = applicationContext.getBean(CadastroCozinha.class);

		cadastroCozinha.listar().forEach(x -> System.out.println(x.getNome()));
	}
}
