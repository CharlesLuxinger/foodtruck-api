package com.charlesluxinger.foodtruck.api.mapper;

import com.charlesluxinger.foodtruck.api.domain.entity.Cidade;
import com.charlesluxinger.foodtruck.api.domain.entity.Estado;
import com.charlesluxinger.foodtruck.api.domain.model.CidadeModel;
import com.charlesluxinger.foodtruck.api.domain.model.payload.CidadePayload;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CidadeMapper {

	@Autowired
	private ModelMapper modelMapper;

	public CidadeModel toModel(Cidade cidade) {
		return modelMapper.map(cidade, CidadeModel.class);
	}

	public List<CidadeModel> toCollectionModel(List<Cidade> cidades) {
		return cidades.stream()
				.map(cidade -> toModel(cidade))
				.collect(Collectors.toList());
	}

	public Cidade toDomainObject(CidadePayload cidadePayload) {
		return modelMapper.map(cidadePayload, Cidade.class);
	}

	public void copyToDomainObject(CidadePayload cidadePayload, Cidade cidade) {
		cidade.setEstado(new Estado());
		modelMapper.map(cidadePayload, cidade);
	}

}
