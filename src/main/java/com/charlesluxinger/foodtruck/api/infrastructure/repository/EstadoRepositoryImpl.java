package com.charlesluxinger.foodtruck.api.infrastructure.repository;

import com.charlesluxinger.foodtruck.api.domain.model.Estado;
import com.charlesluxinger.foodtruck.api.domain.repository.EstadoRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class EstadoRepositoryImpl implements EstadoRepository {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Estado> findAll() {
		return manager.createQuery("from Estado", Estado.class).getResultList();
	}

	@Override
	public Estado findById(Long id) {
		return manager.find(Estado.class, id);
	}


	@Override
	@Transactional
	public Estado save(Estado estado) {
		return manager.merge(estado);
	}

	@Override
	@Transactional
	public void remove(Long id) {
		Estado estadoFound = findById(id);
		if (estadoFound == null) {
			throw new EmptyResultDataAccessException(1);
		}
		manager.remove(estadoFound);
	}

}
