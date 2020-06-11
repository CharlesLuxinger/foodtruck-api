package com.charlesluxinger.foodtruck.api.config;

import com.charlesluxinger.foodtruck.api.domain.entity.Endereco;
import com.charlesluxinger.foodtruck.api.domain.model.EnderecoModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper(){
		var modelMapper = new ModelMapper();

		var enderecoModelTypeMap = modelMapper.createTypeMap(Endereco.class, EnderecoModel.class);

		enderecoModelTypeMap.<String>addMapping(
				src -> src.getCidade().getEstado().getNome(),
				(target, value) -> target.getCidade().setEstado(value)
		);

		return modelMapper;
	}

}