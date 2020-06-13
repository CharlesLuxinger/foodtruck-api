package com.charlesluxinger.foodtruck.api.domain.service;

import com.charlesluxinger.foodtruck.api.domain.entity.Pedido;
import com.charlesluxinger.foodtruck.api.domain.exception.PedidoNotFoundException;
import com.charlesluxinger.foodtruck.api.domain.repository.PedidoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PedidoService {

	private final PedidoRepository pedidoRepository;

	public Pedido findById(Long pedidoId) {
		return pedidoRepository.findById(pedidoId)
							   .orElseThrow(() -> new PedidoNotFoundException(pedidoId));
	}

}