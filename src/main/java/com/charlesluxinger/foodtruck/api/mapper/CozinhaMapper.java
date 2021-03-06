package com.charlesluxinger.foodtruck.api.mapper;

import com.charlesluxinger.foodtruck.api.domain.entity.Cozinha;
import com.charlesluxinger.foodtruck.api.domain.model.CozinhaModel;
import com.charlesluxinger.foodtruck.api.domain.model.payload.CozinhaPayload;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CozinhaMapper {

	private final ModelMapper modelMapper;

	public CozinhaModel toModel(Cozinha cozinha) {
		return modelMapper.map(cozinha, CozinhaModel.class);
	}

	public List<CozinhaModel> toCollectionModel(List<Cozinha> cozinhas) {
		return cozinhas.stream()
				.map(cozinha -> toModel(cozinha))
				.collect(Collectors.toList());
	}

	public Cozinha toDomainObject(CozinhaPayload cozinhaPayload) {
		return modelMapper.map(cozinhaPayload, Cozinha.class);
	}

	public void copyToDomainObject(CozinhaPayload cozinhaPayload, Cozinha cozinha) {
		modelMapper.map(cozinhaPayload, cozinha);
	}
}
