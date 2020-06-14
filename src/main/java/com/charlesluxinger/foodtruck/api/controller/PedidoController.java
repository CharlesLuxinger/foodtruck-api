package com.charlesluxinger.foodtruck.api.controller;

import com.charlesluxinger.foodtruck.api.domain.entity.Usuario;
import com.charlesluxinger.foodtruck.api.domain.exception.DomainException;
import com.charlesluxinger.foodtruck.api.domain.exception.EntityNotFoundException;
import com.charlesluxinger.foodtruck.api.domain.model.PedidoModel;
import com.charlesluxinger.foodtruck.api.domain.model.PedidoResumoModel;
import com.charlesluxinger.foodtruck.api.domain.model.payload.PedidoPayload;
import com.charlesluxinger.foodtruck.api.domain.repository.PedidoRepository;
import com.charlesluxinger.foodtruck.api.domain.service.PedidoService;
import com.charlesluxinger.foodtruck.api.mapper.PedidoMapper;
import com.charlesluxinger.foodtruck.api.mapper.PedidoResumoMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoModel save(@Valid @RequestBody PedidoPayload pedidoPayload) {
		try {
			var novoPedido = pedidoMapper.toDomainObject(pedidoPayload);

			// TODO pegar usuário autenticado
			novoPedido.setCliente(new Usuario());
			novoPedido.getCliente().setId(1L);

			novoPedido = pedidoService.emitir(novoPedido);

			return pedidoMapper.toModel(novoPedido);
		} catch (EntityNotFoundException e) {
			throw new DomainException(e.getMessage(), e);
		}
	}

}