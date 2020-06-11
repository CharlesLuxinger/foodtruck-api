package com.charlesluxinger.foodtruck.api.mapper;

import com.charlesluxinger.foodtruck.api.domain.entity.Cozinha;
import com.charlesluxinger.foodtruck.api.domain.entity.Endereco;
import com.charlesluxinger.foodtruck.api.domain.entity.Restaurante;
import com.charlesluxinger.foodtruck.api.domain.model.RestauranteModel;
import com.charlesluxinger.foodtruck.api.domain.model.payload.RestaurantePayload;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class RestauranteMapper {

	private final ModelMapper modelMapper;

	public RestauranteModel toModel(Restaurante restaurante){
		return modelMapper.map(restaurante, RestauranteModel.class);
	}

	public List<RestauranteModel> toCollectionModel(List<Restaurante> restaurantes){
		return restaurantes.stream()
						   .map(r -> toModel(r))
						   .collect(Collectors.toList());
	}

	public Restaurante toDomainObject(RestaurantePayload restaurante){
		return modelMapper.map(restaurante, Restaurante.class);
	}

	public void copyToDomainObject(RestaurantePayload restaurantePayload, Restaurante restaurante){
		restaurante.setCozinha(new Cozinha());
		restaurante.setEndereco(new Endereco());
		modelMapper.map(restaurantePayload, restaurante);
	}

}