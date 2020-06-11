package com.charlesluxinger.foodtruck.api.controller;

import com.charlesluxinger.foodtruck.api.domain.entity.Grupo;
import com.charlesluxinger.foodtruck.api.domain.model.GrupoModel;
import com.charlesluxinger.foodtruck.api.domain.model.payload.GrupoPayload;
import com.charlesluxinger.foodtruck.api.domain.repository.GrupoRepository;
import com.charlesluxinger.foodtruck.api.domain.service.GrupoService;
import com.charlesluxinger.foodtruck.api.mapper.GrupoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

	private GrupoRepository grupoRepository;
	private GrupoService grupoService;
	private GrupoMapper grupoMapper;

	@GetMapping
	public List<GrupoModel> findAll() {
		List<Grupo> todosGrupos = grupoRepository.findAll();

		return grupoMapper.toCollectionModel(todosGrupos);
	}

	@GetMapping("/{grupoId}")
	public GrupoModel buscar(@PathVariable Long grupoId) {
		Grupo grupo = grupoService.findById(grupoId);

		return grupoMapper.toModel(grupo);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GrupoModel save(@RequestBody @Valid GrupoPayload grupoPayload) {
		Grupo grupo = grupoMapper.toDomainObject(grupoPayload);

		grupo = grupoService.save(grupo);

		return grupoMapper.toModel(grupo);
	}

	@PutMapping("/{grupoId}")
	public GrupoModel update(@PathVariable Long grupoId, @RequestBody @Valid GrupoPayload grupoPayload) {
		Grupo grupoAtual = grupoService.findById(grupoId);

		grupoMapper.copyToDomainObject(grupoPayload, grupoAtual);

		grupoAtual = grupoService.save(grupoAtual);

		return grupoMapper.toModel(grupoAtual);
	}

	@DeleteMapping("/{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long grupoId) {
		grupoService.remove(grupoId);
	}

}