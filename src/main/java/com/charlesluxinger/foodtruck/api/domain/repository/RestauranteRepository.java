package com.charlesluxinger.foodtruck.api.domain.repository;

import java.util.List;

import com.charlesluxinger.foodtruck.api.domain.model.Restaurante;

public interface RestauranteRepository {

	public List<Restaurante> findAll();

	public Restaurante findById(Long id);

	public Restaurante save(Restaurante restaurante);

	public void remove(Restaurante restaurante);
}
