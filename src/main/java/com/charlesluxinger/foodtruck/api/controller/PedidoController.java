package com.charlesluxinger.foodtruck.api.controller;

import com.charlesluxinger.foodtruck.api.domain.model.PedidoModel;
import com.charlesluxinger.foodtruck.api.domain.model.PedidoResumoModel;
import com.charlesluxinger.foodtruck.api.domain.repository.PedidoRepository;
import com.charlesluxinger.foodtruck.api.domain.service.PedidoService;
import com.charlesluxinger.foodtruck.api.mapper.PedidoMapper;
import com.charlesluxinger.foodtruck.api.mapper.PedidoResumoMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/pedidos")
@AllArgsConstructor
public class PedidoController {

	private final PedidoRepository pedidoRepository;
	private final PedidoService pedidoService;
	private final PedidoMapper pedidoMapper;
	private final PedidoResumoMapper pedidoResumoMapper;

	@GetMapping
	public List<PedidoResumoModel> findAll() {
		var pedidos = pedidoRepository.findAll();
		return pedidoResumoMapper.toCollectionModel(pedidos);
	}

	@GetMapping("/{pedidoId}")
	public PedidoModel findById(@PathVariable Long pedidoId) {
		var pedido = pedidoService.findById(pedidoId);
		return pedidoMapper.toModel(pedido);
	}

}