package com.charlesluxinger.foodtruck.api.controller;

import com.charlesluxinger.foodtruck.api.domain.model.UsuarioModel;
import com.charlesluxinger.foodtruck.api.domain.service.RestauranteService;
import com.charlesluxinger.foodtruck.api.mapper.UsuarioMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/restaurantes/{restauranteId}/responsaveis")
@AllArgsConstructor
public class RestauranteUsuarioResponsavelController {

	private final RestauranteService restauranteService;
	private final UsuarioMapper usuarioMapper;

	@GetMapping
	public List<UsuarioModel> findAll(@PathVariable Long restauranteId) {
		var restaurante = restauranteService.findById(restauranteId);
		return usuarioMapper.toCollectionModel(restaurante.getResponsaveis());
	}

	@DeleteMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removeResponsavel(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
		restauranteService.removeResponsavel(restauranteId, usuarioId);
	}

	@PutMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void addResponsavel(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
		restauranteService.addResponsavel(restauranteId, usuarioId);
	}

}