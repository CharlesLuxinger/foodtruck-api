package com.charlesluxinger.foodtruck.api.config;

import com.charlesluxinger.foodtruck.api.domain.entity.Endereco;
import com.charlesluxinger.foodtruck.api.domain.entity.ItemPedido;
import com.charlesluxinger.foodtruck.api.domain.model.EnderecoModel;
import com.charlesluxinger.foodtruck.api.domain.model.payload.ItemPedidoPayload;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper(){
		var modelMapper = new ModelMapper();

		modelMapper.createTypeMap(Endereco.class, EnderecoModel.class)
				   .<String>addMapping( src -> src.getCidade().getEstado().getNome(), (target, value) -> target.getCidade().setEstado(value));

		modelMapper.createTypeMap(ItemPedidoPayload.class, ItemPedido.class)
				   .addMappings(mapper -> mapper.skip(ItemPedido::setId));

		return modelMapper;
	}

}