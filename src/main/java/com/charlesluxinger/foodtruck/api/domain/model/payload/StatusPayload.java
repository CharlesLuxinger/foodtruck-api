package com.charlesluxinger.foodtruck.api.domain.model.payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class StatusPayload {

	@NotNull
	private Boolean status;

}
