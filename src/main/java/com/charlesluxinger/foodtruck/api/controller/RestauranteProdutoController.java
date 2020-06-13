package com.charlesluxinger.foodtruck.api.controller;

import com.charlesluxinger.foodtruck.api.domain.model.ProdutoModel;
import com.charlesluxinger.foodtruck.api.domain.model.payload.ProdutoPayload;
import com.charlesluxinger.foodtruck.api.domain.repository.ProdutoRepository;
import com.charlesluxinger.foodtruck.api.domain.service.ProdutoService;
import com.charlesluxinger.foodtruck.api.domain.service.RestauranteService;
import com.charlesluxinger.foodtruck.api.mapper.ProdutoMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos")
@AllArgsConstructor
public class RestauranteProdutoController {

	private final ProdutoRepository produtoRepository;
	private final ProdutoService produtoService;
	private final RestauranteService cadastroRestaurante;
	private final ProdutoMapper produtoMapper;

	@GetMapping
	public List<ProdutoModel> findAll(@PathVariable Long restauranteId) {
		var restaurante = cadastroRestaurante.findById(restauranteId);

		var todosProdutos = produtoRepository.findByRestaurante(restaurante);

		return produtoMapper.toCollectionModel(todosProdutos);
	}

	@GetMapping("/{produtoId}")
	public ProdutoModel findByRestauranteIdAndProdutoId(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
		var produto = produtoService.findByRestauranteIdAndProdutoId(restauranteId, produtoId);

		return produtoMapper.toModel(produto);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoModel save(@PathVariable Long restauranteId,
	                              @RequestBody @Valid ProdutoPayload produtoPayload) {
		var restaurante = cadastroRestaurante.findById(restauranteId);

		var produto = produtoMapper.toDomainObject(produtoPayload);
		produto.setRestaurante(restaurante);

		produto = produtoService.save(produto);

		return produtoMapper.toModel(produto);
	}

	@PutMapping("/{produtoId}")
	public ProdutoModel update(@PathVariable Long restauranteId, @PathVariable Long produtoId,
	                              @RequestBody @Valid ProdutoPayload produtoPayload) {
		var produtoAtual = produtoService.findByRestauranteIdAndProdutoId(restauranteId, produtoId);

		produtoMapper.copyToDomainObject(produtoPayload, produtoAtual);

		produtoAtual = produtoService.save(produtoAtual);

		return produtoMapper.toModel(produtoAtual);
	}
	
}