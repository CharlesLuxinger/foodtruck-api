package com.charlesluxinger.foodtruck.api.domain.repository;

import com.charlesluxinger.foodtruck.api.domain.model.Cidade;

import java.util.List;

public interface CidadeRepository {

	List<Cidade> findAll();

	Cidade findById(Long id);

	Cidade save(Cidade cidade);

	void remove(Long id);
}
