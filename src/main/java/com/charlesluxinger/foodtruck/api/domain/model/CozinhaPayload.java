package com.charlesluxinger.foodtruck.api.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class CozinhaPayload {

	@NotNull
	private Long id;

}
