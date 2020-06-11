package com.charlesluxinger.foodtruck.api.domain.model.payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class SenhaPayload {

	@NotBlank
	private String senhaAtual;

	@NotBlank
	private String novaSenha;

}