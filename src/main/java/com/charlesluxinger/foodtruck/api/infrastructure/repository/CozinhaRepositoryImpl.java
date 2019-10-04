package com.charlesluxinger.foodtruck.api.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.charlesluxinger.foodtruck.api.domain.model.Cozinha;
import com.charlesluxinger.foodtruck.api.domain.repository.CozinhaRepository;

@Component
public class CozinhaRepositoryImpl implements CozinhaRepository {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Cozinha> findAll() {
		return manager.createQuery("from Cozinha", Cozinha.class).getResultList();
	}

	@Override
	public Cozinha findById(Long id) {
		return manager.find(Cozinha.class, id);
	}


	@Override
	@Transactional
	public Cozinha save(Cozinha cozinha) {
		return manager.merge(cozinha);
	}

	@Override
	@Transactional
	public void remove(Cozinha cozinha) {
		Cozinha cozinhaFound = findById(cozinha.getId());
		if (cozinhaFound != null) {
			manager.remove(cozinhaFound);
		}
	}

}
