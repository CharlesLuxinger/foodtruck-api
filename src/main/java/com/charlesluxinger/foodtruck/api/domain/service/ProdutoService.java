package com.charlesluxinger.foodtruck.api.domain.service;

import com.charlesluxinger.foodtruck.api.domain.entity.Produto;
import com.charlesluxinger.foodtruck.api.domain.exception.ProdutoNotFoundException;
import com.charlesluxinger.foodtruck.api.domain.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ProdutoService {

	private final ProdutoRepository produtoRepository;

	public Produto findByRestauranteIdAndProdutoId(Long restauranteId, Long produtoId) {
		return produtoRepository.findById(restauranteId, produtoId)
				.orElseThrow(() -> new ProdutoNotFoundException(restauranteId, produtoId));
	}

	@Transactional
	public Produto save(Produto produto) {
		return produtoRepository.save(produto);
	}

}