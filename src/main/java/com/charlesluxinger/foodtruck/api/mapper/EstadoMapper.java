package com.charlesluxinger.foodtruck.api.mapper;

import com.charlesluxinger.foodtruck.api.domain.entity.Estado;
import com.charlesluxinger.foodtruck.api.domain.model.EstadoModel;
import com.charlesluxinger.foodtruck.api.domain.model.payload.EstadoPayload;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class EstadoMapper {

	private final ModelMapper modelMapper;

	public EstadoModel toModel(Estado estado) {
		return modelMapper.map(estado, EstadoModel.class);
	}

	public List<EstadoModel> toCollectionModel(List<Estado> estados) {
		return estados.stream()
				.map(estado -> toModel(estado))
				.collect(Collectors.toList());
	}

	public Estado toDomainObject(EstadoPayload estadoPayload) {
		return modelMapper.map(estadoPayload, Estado.class);
	}

	public void copyToDomainObject(EstadoPayload estadoPayload, Estado estado) {
		modelMapper.map(estadoPayload, estado);
	}

}
