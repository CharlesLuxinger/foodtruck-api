package com.charlesluxinger.foodtruck.api.mapper;

import com.charlesluxinger.foodtruck.api.domain.entity.Usuario;
import com.charlesluxinger.foodtruck.api.domain.model.UsuarioModel;
import com.charlesluxinger.foodtruck.api.domain.model.payload.UsuarioPayload;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UsuarioMapper {

	private final ModelMapper modelMapper;

	public UsuarioModel toModel(Usuario usuario) {
		return modelMapper.map(usuario, UsuarioModel.class);
	}

	public List<UsuarioModel> toCollectionModel(List<Usuario> usuarios) {
		return usuarios.stream()
				.map(usuario -> toModel(usuario))
				.collect(Collectors.toList());
	}

	public Usuario toDomainObject(UsuarioPayload usuarioPayload) {
		return modelMapper.map(usuarioPayload, Usuario.class);
	}

	public void copyToDomainObject(UsuarioPayload usuarioPayload, Usuario usuario) {
		modelMapper.map(usuarioPayload, usuario);
	}

}