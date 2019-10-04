package com.charlesluxinger.foodtruck.api.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.charlesluxinger.foodtruck.api.domain.model.Estado;
import com.charlesluxinger.foodtruck.api.domain.repository.EstadoRepository;

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
	public void remove(Estado estado) {
		Estado estadoFound = findById(estado.getId());
		if (estadoFound != null) {
			manager.remove(estadoFound);
		}
	}

}
