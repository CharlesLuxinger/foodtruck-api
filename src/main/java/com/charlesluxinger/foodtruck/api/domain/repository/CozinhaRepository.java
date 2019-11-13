package com.charlesluxinger.foodtruck.api.domain.repository;

import com.charlesluxinger.foodtruck.api.domain.model.Cozinha;

import java.util.List;

public interface CozinhaRepository {

	List<Cozinha> findAll();

	Cozinha findById(Long id);

	Cozinha save(Cozinha cozinha);

	void remove(Long id);
}
