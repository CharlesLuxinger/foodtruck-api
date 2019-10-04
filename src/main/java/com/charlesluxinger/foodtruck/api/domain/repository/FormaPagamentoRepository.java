package com.charlesluxinger.foodtruck.api.domain.repository;

import java.util.List;

import com.charlesluxinger.foodtruck.api.domain.model.FormaPagamento;

public interface FormaPagamentoRepository {

	public List<FormaPagamento> findAll();

	public FormaPagamento findById(Long id);

	public FormaPagamento save(FormaPagamento formaPagamento);

	public void remove(FormaPagamento formaPagamento);
}
