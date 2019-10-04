package com.charlesluxinger.foodtruck.api.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.charlesluxinger.foodtruck.api.domain.model.Restaurante;
import com.charlesluxinger.foodtruck.api.domain.repository.RestauranteRepository;

@Component
public class RestauranteRepositoryImpl implements RestauranteRepository {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Restaurante> findAll() {
		return manager.createQuery("from Restaurante", Restaurante.class).getResultList();
	}

	@Override
	public Restaurante findById(Long id) {
		return manager.find(Restaurante.class, id);
	}


	@Override
	@Transactional
	public Restaurante save(Restaurante restaurante) {
		return manager.merge(restaurante);
	}

	@Override
	@Transactional
	public void remove(Restaurante restaurante) {
		Restaurante restauranteFound = findById(restaurante.getId());
		if (restauranteFound != null) {
			manager.remove(restauranteFound);
		}
	}

}
