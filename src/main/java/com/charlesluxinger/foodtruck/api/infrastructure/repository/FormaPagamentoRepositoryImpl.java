package com.charlesluxinger.foodtruck.api.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.charlesluxinger.foodtruck.api.domain.model.FormaPagamento;
import com.charlesluxinger.foodtruck.api.domain.repository.FormaPagamentoRepository;

@Component
public class FormaPagamentoRepositoryImpl implements FormaPagamentoRepository {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<FormaPagamento> findAll() {
		return manager.createQuery("from FormaPagamento", FormaPagamento.class).getResultList();
	}

	@Override
	public FormaPagamento findById(Long id) {
		return manager.find(FormaPagamento.class, id);
	}


	@Override
	@Transactional
	public FormaPagamento save(FormaPagamento cidade) {
		return manager.merge(cidade);
	}

	@Override
	@Transactional
	public void remove(FormaPagamento formaPagamento) {
		FormaPagamento formaFound = findById(formaPagamento.getId());
		if (formaFound != null) {
			manager.remove(formaFound);
		}
	}

}
