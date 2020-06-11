package com.charlesluxinger.foodtruck.api.domain.service;

import com.charlesluxinger.foodtruck.api.domain.entity.Grupo;
import com.charlesluxinger.foodtruck.api.domain.exception.EntityNotFoundException;
import com.charlesluxinger.foodtruck.api.domain.exception.GrupoNotFoundException;
import com.charlesluxinger.foodtruck.api.domain.repository.GrupoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class GrupoService {

	private static final String MSG_GRUPO_EM_USO = "Grupo de código %d não pode ser removido, pois está em uso";

	private final GrupoRepository grupoRepository;

	@Transactional
	public Grupo save(Grupo grupo) {
		return grupoRepository.save(grupo);
	}

	@Transactional
	public void remove(Long grupoId) {
		try {
			grupoRepository.deleteById(grupoId);
			grupoRepository.flush();

		} catch (EmptyResultDataAccessException e) {
			throw new GrupoNotFoundException(grupoId);

		} catch (DataIntegrityViolationException e) {
			throw new EntityNotFoundException(
					String.format(MSG_GRUPO_EM_USO, grupoId));
		}
	}

	public Grupo findById(Long grupoId) {
		return grupoRepository.findById(grupoId)
				.orElseThrow(() -> new GrupoNotFoundException(grupoId));
	}

}