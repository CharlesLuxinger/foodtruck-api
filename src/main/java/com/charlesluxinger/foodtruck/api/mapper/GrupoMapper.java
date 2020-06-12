package com.charlesluxinger.foodtruck.api.mapper;

import com.charlesluxinger.foodtruck.api.domain.entity.Grupo;
import com.charlesluxinger.foodtruck.api.domain.model.GrupoModel;
import com.charlesluxinger.foodtruck.api.domain.model.payload.GrupoPayload;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class GrupoMapper {

	private final ModelMapper modelMapper;

	public GrupoModel toModel(Grupo grupo) {
		return modelMapper.map(grupo, GrupoModel.class);
	}

	public List<GrupoModel> toCollectionModel(List<Grupo> grupos) {
		return grupos.stream()
				.map(grupo -> toModel(grupo))
				.collect(Collectors.toList());
	}

	public Grupo toDomainObject(GrupoPayload grupoPayload) {
		return modelMapper.map(grupoPayload, Grupo.class);
	}

	public void copyToDomainObject(GrupoPayload grupoPayload, Grupo grupo) {
		modelMapper.map(grupoPayload, grupo);
	}
}
