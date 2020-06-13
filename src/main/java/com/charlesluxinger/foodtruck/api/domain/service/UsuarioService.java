package com.charlesluxinger.foodtruck.api.domain.service;

import com.charlesluxinger.foodtruck.api.domain.entity.Usuario;
import com.charlesluxinger.foodtruck.api.domain.exception.DomainException;
import com.charlesluxinger.foodtruck.api.domain.exception.UsuarioNotFoundException;
import com.charlesluxinger.foodtruck.api.domain.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;
	private final GrupoService grupoService;

	@Transactional
	public Usuario save(Usuario usuario) {
		usuarioRepository.detach(usuario);

		var usuarioFound = usuarioRepository.findByEmail(usuario.getEmail());

		if (usuarioFound.isEmpty() || usuarioFound.get().equals(usuario)) {
			return usuarioRepository.save(usuario);
		} else {
			throw new DomainException("Já existe um usuário com o email: " + usuario.getEmail());
		}
	}

	@Transactional
	public void updateSenha(Long usuarioId, String senhaAtual, String novaSenha) {
		var usuario = findById(usuarioId);

		if (!usuario.isSenha(senhaAtual)) {
			throw new DomainException("Senha atual informada não coincide com a senha do usuário.");
		}

		usuario.setSenha(novaSenha);
	}

	public Usuario findById(Long usuarioId) {
		return usuarioRepository.findById(usuarioId)
				.orElseThrow(() -> new UsuarioNotFoundException(usuarioId));
	}

	@Transactional
	public void removeGrupo(Long usuarioId, Long grupoId) {
		var usuario = findById(usuarioId);
		var grupo = grupoService.findById(grupoId);

		usuario.removeGrupo(grupo);
	}

	@Transactional
	public void addGrupo(Long usuarioId, Long grupoId) {
		var usuario = findById(usuarioId);
		var grupo = grupoService.findById(grupoId);

		usuario.addGrupo(grupo);
	}

}