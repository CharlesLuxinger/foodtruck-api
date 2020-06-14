package com.charlesluxinger.foodtruck.api.domain.service;

import com.charlesluxinger.foodtruck.api.domain.entity.Permissao;
import com.charlesluxinger.foodtruck.api.domain.exception.PermissaoNotFoundException;
import com.charlesluxinger.foodtruck.api.domain.repository.PermissaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PermissaoService {

	private final PermissaoRepository permissaoRepository;

	public Permissao findById(Long permissaoId) {
		return permissaoRepository.findById(permissaoId)
				.orElseThrow(() -> new PermissaoNotFoundException(permissaoId));
	}

}