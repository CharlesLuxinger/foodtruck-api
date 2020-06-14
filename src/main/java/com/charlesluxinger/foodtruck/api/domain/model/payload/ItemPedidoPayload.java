package com.charlesluxinger.foodtruck.api.domain.model.payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Setter
@Getter
public class ItemPedidoPayload {

	@NotNull
	private Long produtoId;

	@NotNull
	@PositiveOrZero
	private Integer quantidade;

	private String observacao;

}