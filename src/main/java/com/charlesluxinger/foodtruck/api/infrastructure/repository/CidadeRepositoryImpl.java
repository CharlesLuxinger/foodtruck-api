package com.charlesluxinger.foodtruck.api.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.charlesluxinger.foodtruck.api.domain.model.Cidade;
import com.charlesluxinger.foodtruck.api.domain.repository.CidadeRepository;

@Component
public class CidadeRepositoryImpl implements CidadeRepository {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Cidade> findAll() {
		return manager.createQuery("from Cidade", Cidade.class).getResultList();
	}

	@Override
	public Cidade findById(Long id) {
		return manager.find(Cidade.class, id);
	}


	@Override
	@Transactional
	public Cidade save(Cidade cidade) {
		return manager.merge(cidade);
	}

	@Override
	@Transactional
	public void remove(Cidade cidade) {
		Cidade cidadeFound = findById(cidade.getId());
		if (cidadeFound != null) {
			manager.remove(cidadeFound);
		}
	}

}
