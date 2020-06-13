package com.charlesluxinger.foodtruck.api.controller;

import com.charlesluxinger.foodtruck.api.domain.entity.Usuario;
import com.charlesluxinger.foodtruck.api.domain.model.GrupoModel;
import com.charlesluxinger.foodtruck.api.domain.service.UsuarioService;
import com.charlesluxinger.foodtruck.api.mapper.GrupoMapper;
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
@RequestMapping(value = "/usuarios/{usuarioId}/grupos")
@AllArgsConstructor
public class UsuarioGrupoController {

	private final UsuarioService usuarioService;
	private final GrupoMapper grupoMapper;

	@GetMapping
	public List<GrupoModel> findAll(@PathVariable Long usuarioId) {
		Usuario usuario = usuarioService.findById(usuarioId);

		return grupoMapper.toCollectionModel(usuario.getGrupos());
	}

	@DeleteMapping("/{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removeGrupo(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
		usuarioService.removeGrupo(usuarioId, grupoId);
	}

	@PutMapping("/{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void addGrupo(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
		usuarioService.addGrupo(usuarioId, grupoId);
	}

}