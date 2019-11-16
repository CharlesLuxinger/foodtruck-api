package com.charlesluxinger.foodtruck.api.domain.repository;

import com.charlesluxinger.foodtruck.api.domain.model.Estado;

import java.util.List;

public interface EstadoRepository {

	List<Estado> findAll();

	Estado findById(Long id);

	Estado save(Estado estado);

	void remove(Long id);
}
