package com.charlesluxinger.foodtruck.api.controller;

import com.charlesluxinger.foodtruck.api.domain.entity.Usuario;
import com.charlesluxinger.foodtruck.api.domain.model.UsuarioModel;
import com.charlesluxinger.foodtruck.api.domain.model.payload.SenhaPayload;
import com.charlesluxinger.foodtruck.api.domain.model.payload.UsuarioPayload;
import com.charlesluxinger.foodtruck.api.domain.model.payload.UsuarioSenhaPayload;
import com.charlesluxinger.foodtruck.api.domain.repository.UsuarioRepository;
import com.charlesluxinger.foodtruck.api.domain.service.UsuarioService;
import com.charlesluxinger.foodtruck.api.mapper.UsuarioMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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
@RequestMapping(value = "/usuarios")
@AllArgsConstructor
public class UsuarioController {

	private final UsuarioRepository usuarioRepository;
	private UsuarioService usuarioService;
	private UsuarioMapper usuarioMapper;

	@GetMapping
	public List<UsuarioModel> findAll() {
		return usuarioMapper.toCollectionModel(usuarioRepository.findAll());
	}

	@GetMapping("/{usuarioId}")
	public UsuarioModel findById(@PathVariable Long usuarioId) {
		return usuarioMapper.toModel(usuarioService.findById(usuarioId));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioModel save(@RequestBody @Valid UsuarioSenhaPayload usuarioSenhaPayload) {
		var usuario = usuarioMapper.toDomainObject(usuarioSenhaPayload);
		return usuarioMapper.toModel(usuarioService.save(usuario));
	}

	@PutMapping("/{usuarioId}")
	public UsuarioModel update(@PathVariable Long usuarioId, @RequestBody @Valid UsuarioPayload usuarioPayload) {
		var usuarioAtual = usuarioService.findById(usuarioId);

		usuarioMapper.copyToDomainObject(usuarioPayload, usuarioAtual);

		return usuarioMapper.toModel(usuarioService.save(usuarioAtual));
	}

	@PutMapping("/{usuarioId}/senha")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void alterarSenha(@PathVariable Long usuarioId, @RequestBody @Valid SenhaPayload senha) {
		usuarioService.updateSenha(usuarioId, senha.getSenhaAtual(), senha.getNovaSenha());
	}

}