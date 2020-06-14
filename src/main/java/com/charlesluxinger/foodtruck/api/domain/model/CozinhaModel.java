package com.charlesluxinger.foodtruck.api.domain.model;

import com.charlesluxinger.foodtruck.api.domain.model.view.RestauranteView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CozinhaModel {

	private Long id;

	@JsonView(RestauranteView.Resumo.class)
	private String nome;

}
