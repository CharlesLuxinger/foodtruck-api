package com.charlesluxinger.foodtruck.api.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class FormaPagamentoModel {

	private Long id;
	private String descricao;

}