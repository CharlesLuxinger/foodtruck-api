package com.charlesluxinger.foodtruck.api.domain.repository;

import java.util.List;

import com.charlesluxinger.foodtruck.api.domain.model.Permissao;

public interface PermissaoRepository {

	public List<Permissao> findAll();

	public Permissao findById(Long id);

	public Permissao save(Permissao permissao);

	public void remove(Permissao permissao);
}
