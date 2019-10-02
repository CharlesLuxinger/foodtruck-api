package com.charlesluxinger.foodtruck.api.domain.repository;

import java.util.List;

import com.charlesluxinger.foodtruck.api.domain.model.Cozinha;

public interface CozinhaRepository {

	public List<Cozinha> findAll();

	public Cozinha findById(Integer id);

	public Cozinha save(Cozinha cozinha);

	public void remove(Cozinha cozinha);
}
