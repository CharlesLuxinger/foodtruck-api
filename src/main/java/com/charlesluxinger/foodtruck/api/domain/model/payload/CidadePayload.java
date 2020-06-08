package com.charlesluxinger.foodtruck.api.domain.model.payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class CidadePayload {

	@NotBlank
	private String nome;

	@Valid
	@NotNull
	private EstadoIdPayload estado;

}
