package com.charlesluxinger.foodtruck.api.mapper;

import com.charlesluxinger.foodtruck.api.domain.entity.Pedido;
import com.charlesluxinger.foodtruck.api.domain.model.PedidoModel;
import com.charlesluxinger.foodtruck.api.domain.model.payload.PedidoPayload;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class PedidoMapper {

	private final ModelMapper modelMapper;

	public PedidoModel toModel(Pedido pedido) {
		return modelMapper.map(pedido, PedidoModel.class);
	}

	public List<PedidoModel> toCollectionModel(List<Pedido> pedidos) {
		return pedidos.stream()
				.map(pedido -> toModel(pedido))
				.collect(Collectors.toList());
	}

	public Pedido toDomainObject(PedidoPayload pedidoPayload) {
		return modelMapper.map(pedidoPayload, Pedido.class);
	}

	public void copyToDomainObject(PedidoPayload pedidoPayload, Pedido pedido) {
		modelMapper.map(pedidoPayload, pedido);
	}

}