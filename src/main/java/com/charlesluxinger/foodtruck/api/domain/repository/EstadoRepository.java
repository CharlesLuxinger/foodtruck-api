package com.charlesluxinger.foodtruck.api.domain.repository;

import java.util.List;

import com.charlesluxinger.foodtruck.api.domain.model.Estado;

public interface EstadoRepository {

	public List<Estado> findAll();

	public Estado findById(Long id);

	public Estado save(Estado estado);

	public void remove(Estado estado);
}
