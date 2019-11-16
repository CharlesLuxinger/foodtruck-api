package com.charlesluxinger.foodtruck.api.infrastructure.repository;

import com.charlesluxinger.foodtruck.api.domain.model.Cidade;
import com.charlesluxinger.foodtruck.api.domain.repository.CidadeRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

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
	public void remove(Long id) {
		Cidade cidadeFound = findById(id);
		if (cidadeFound == null) {
			throw new EmptyResultDataAccessException(1);
		}
		manager.remove(cidadeFound);
	}

}
