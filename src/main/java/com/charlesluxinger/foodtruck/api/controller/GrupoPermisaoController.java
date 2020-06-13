package com.charlesluxinger.foodtruck.api.controller;

import com.charlesluxinger.foodtruck.api.domain.model.PermissaoModel;
import com.charlesluxinger.foodtruck.api.domain.service.GrupoService;
import com.charlesluxinger.foodtruck.api.mapper.PermissaoMapper;
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
@RequestMapping(value = "/grupos/{grupoId}/permissoes")
@AllArgsConstructor
public class GrupoPermisaoController {

	private final GrupoService grupoService;
	private final PermissaoMapper permissaoMapper;

	@GetMapping
	public List<PermissaoModel> findAll(@PathVariable Long grupoId) {
		var grupo = grupoService.findById(grupoId);

		return permissaoMapper.toCollectionModel(grupo.getPermissoes());
	}

	@DeleteMapping("/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removePermissao(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		grupoService.removePermissao(grupoId, permissaoId);
	}

	@PutMapping("/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void addPermissao(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		grupoService.addPermissao(grupoId, permissaoId);
	}

}