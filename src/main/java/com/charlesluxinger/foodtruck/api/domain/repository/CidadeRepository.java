package com.charlesluxinger.foodtruck.api.domain.repository;

import java.util.List;

import com.charlesluxinger.foodtruck.api.domain.model.Cidade;

public interface CidadeRepository {

	public List<Cidade> findAll();

	public Cidade findById(Long id);

	public Cidade save(Cidade cidade);

	public void remove(Cidade cidade);
}
