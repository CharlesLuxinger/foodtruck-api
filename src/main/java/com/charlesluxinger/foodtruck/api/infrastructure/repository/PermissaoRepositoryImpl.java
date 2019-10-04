package com.charlesluxinger.foodtruck.api.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.charlesluxinger.foodtruck.api.domain.model.Permissao;
import com.charlesluxinger.foodtruck.api.domain.repository.PermissaoRepository;

@Component
public class PermissaoRepositoryImpl implements PermissaoRepository {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Permissao> findAll() {
		return manager.createQuery("from Permissao", Permissao.class).getResultList();
	}

	@Override
	public Permissao findById(Long id) {
		return manager.find(Permissao.class, id);
	}


	@Override
	@Transactional
	public Permissao save(Permissao permissao) {
		return manager.merge(permissao);
	}

	@Override
	@Transactional
	public void remove(Permissao permissao) {
		Permissao permissaoFound = findById(permissao.getId());
		if (permissaoFound != null) {
			manager.remove(permissaoFound);
		}
	}

}
