package com.charlesluxinger.foodtruck.api.domain.repository;

import com.charlesluxinger.foodtruck.api.domain.model.Restaurante;

import java.util.List;

public interface RestauranteRepository {

	List<Restaurante> findAll();

	Restaurante findById(Long id);

	Restaurante save(Restaurante restaurante);

	void remove(Long id);
}
