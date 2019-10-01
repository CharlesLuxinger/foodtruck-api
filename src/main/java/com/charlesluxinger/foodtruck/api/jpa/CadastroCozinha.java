package com.charlesluxinger.foodtruck.api.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.charlesluxinger.foodtruck.api.domain.model.Cozinha;

@Component
public class CadastroCozinha {

	@PersistenceContext
	private EntityManager manager;

	public Cozinha findById(Integer id) {
		return manager.find(Cozinha.class, id);
	}

	public List<Cozinha> listar() {
		return manager.createQuery("from Cozinha", Cozinha.class).getResultList();
	}

	@Transactional
	public Cozinha save(Cozinha cozinha) {
		return manager.merge(cozinha);
	}

	@Transactional
	public void remove(Cozinha cozinha) {
		Cozinha cozinhaFound = findById(cozinha.getId());
		if (cozinhaFound != null) {
			manager.remove(cozinhaFound);
		}
	}
}
