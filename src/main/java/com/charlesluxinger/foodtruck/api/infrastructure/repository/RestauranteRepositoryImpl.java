package com.charlesluxinger.foodtruck.api.infrastructure.repository;

import com.charlesluxinger.foodtruck.api.domain.model.Restaurante;
import com.charlesluxinger.foodtruck.api.domain.repository.RestauranteRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

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
	public void remove(Long id) {
		Restaurante restauranteFound = findById(id);
		if (restauranteFound == null) {
			throw new EmptyResultDataAccessException(1);
		}
		manager.remove(restauranteFound);
	}

}
