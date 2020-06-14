package com.charlesluxinger.foodtruck.api.domain.model.payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Setter
@Getter
public class PedidoPayload {

	@Valid
	@NotNull
	private RestauranteIdPayload restaurante;

	@Valid
	@NotNull
	private EnderecoPayload enderecoEntrega;

	@Valid
	@NotNull
	private FormaPagamentoIdPayload formaPagamento;

	@Valid
	@Size(min = 1)
	@NotNull
	private List<ItemPedidoPayload> itens;

}