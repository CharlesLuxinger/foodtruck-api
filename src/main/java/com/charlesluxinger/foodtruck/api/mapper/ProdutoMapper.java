package com.charlesluxinger.foodtruck.api.mapper;

import com.charlesluxinger.foodtruck.api.domain.entity.Produto;
import com.charlesluxinger.foodtruck.api.domain.model.ProdutoModel;
import com.charlesluxinger.foodtruck.api.domain.model.payload.ProdutoPayload;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ProdutoMapper {

	private final ModelMapper modelMapper;

	public ProdutoModel toModel(Produto produto) {
		return modelMapper.map(produto, ProdutoModel.class);
	}

	public List<ProdutoModel> toCollectionModel(List<Produto> produtos) {
		return produtos.stream()
				.map(produto -> toModel(produto))
				.collect(Collectors.toList());
	}

	public Produto toDomainObject(ProdutoPayload produtoPayload) {
		return modelMapper.map(produtoPayload, Produto.class);
	}

	public void copyToDomainObject(ProdutoPayload produtoPayload, Produto produto) {
		modelMapper.map(produtoPayload, produto);
	}

}